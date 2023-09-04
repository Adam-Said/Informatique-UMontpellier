# librairies générales
import pandas as pd
import time
import numpy as np

# librairie affichage
import matplotlib.pyplot as plt
import seaborn as sns

# librairie scikit-learn
from sklearn import metrics
from sklearn.model_selection import cross_val_score
from sklearn.metrics import confusion_matrix
from sklearn.metrics import classification_report
from sklearn.model_selection import KFold
from sklearn.model_selection import GridSearchCV
from sklearn.metrics import accuracy_score


# classe pour afficher proprement les paramètres d'un classifieur
class Result_Parameters:
     def __init__(self,name, score, parameters):
         self.name = name
         self.score = score
         self.parameters = parameters
     def __repr__(self):
         return repr((self.name, self.score, self.parameters))

# classe pour afficher proprement les résultats d'une classification
class Result:
     def __init__(self,name, scoremean, stdresult,timespent):
         self.name = name
         self.scoremean = scoremean
         self.stdresult = stdresult
         self.timespent = timespent
     def __repr__(self):
         return repr((self.name, self.scoremean, self.stdresult,self.timespent))


# fonction utilisée pour l'affichage de la matrice de confusion
def plot_confusion_matrix(confusionmatrix, classes):
    sns.set(color_codes=True)
    plt.figure(1, figsize=(8, 5))
 
    plt.title("Matrice de confusion")
 
    sns.set(font_scale=1.4)
    ax = sns.heatmap(confusionmatrix, annot=True, cmap="YlGnBu", cbar_kws={'label': 'Scale'},fmt='g')
 
    ax.set_xticklabels(classes,rotation=45)
    ax.set_yticklabels(classes,rotation=0)
 
    ax.set(ylabel="True Label", xlabel="Predicted Label")
    plt.show()
    plt.close()   
    
# fonction qui affiche le classification report et la matrice de confusion
def MyshowAllScores(y_test,y_pred):
  classes= np.unique(y_test)
  print("Accuracy : %0.3f"%(accuracy_score(y_test,y_pred)))
  print("Classification Report")
  print(classification_report(y_test,y_pred,digits=5))    
  cnf_matrix = confusion_matrix(y_test,y_pred)
  plot_confusion_matrix(cnf_matrix, classes)


# fonction qui teste un ensemble de pipeline et affiche les meilleurs résultats triés par score    
def MyTestPipelines(models,X,y,score='accuracy'):
  seed = 7        
  allresults = []
  results = []
  names = []
  for name,model in models:
    # cross validation en 10 fois
    kfold = KFold(n_splits=10, random_state=seed)
    
    print ("Evaluation de ",name)
    start_time = time.time()
    # application de la classification
    cv_results = cross_val_score(model, X, y, cv=kfold, scoring=score)
    
    # pour afficher les paramètres du modèle en cours et la taille du vecteur intermédiaire
    # enlever le commentaire des deux lignes suivantes 
    #print ("paramètre du modèle ",model.get_params(),'\n')
    #print ("taille du vecteur : ",(model.named_steps['tfidf_vectorizer'].fit_transform(X)).shape,'\n')

    thetime=time.time() - start_time
    result=Result(name,cv_results.mean(),cv_results.std(),thetime)
    allresults.append(result)
    # pour affichage
    results.append(cv_results)
    names.append(name)
    print("%s : %0.3f (%0.3f) in %0.3f s" % (name, cv_results.mean(), cv_results.std(),thetime))         
    
  allresults=sorted(allresults, key=lambda result: result.scoremean, reverse=True) 

  # affichage des résultats
  print ('\nLe meilleur resultat : ')
  print ('Classifier : ',allresults[0].name, 
       ' %s : %0.3f' %(score,allresults[0].scoremean), 
       ' (%0.3f)'%allresults[0].stdresult,  
       ' en %0.3f '%allresults[0].timespent,' s\n')

  print ('Tous les résultats : \n')
  for result in allresults:
    print ('Classifier : ',result.name, 
       ' %s : %0.3f' %(score,result.scoremean), 
       ' (%0.3f)'%result.stdresult,  
       ' en %0.3f '%result.timespent,' s')
  return allresults      
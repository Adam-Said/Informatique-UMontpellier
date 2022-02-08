#lang racket

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; 1 Representation des propositions

(define F 'p)
(define G '(! toto))
(define H '(<-> (^ a c) (v (! b) (-> c (^ Bot Top)))))

(define (neg? F) (eq? F '!))
(define (and? F) (eq? F '^))
(define (or? F) (eq? F 'v))
(define (imp? F) (eq? F '->))
(define (equ? F) (eq? F '<->))
(define (top? F) (eq? F 'Top))
(define (bot? F) (eq? F 'Bot))
(define (symbLog? F) (or (top? F) (bot? F) (and? F) (or? F) (neg? F) (imp? F) (equ? F)))
(define (conBin? F) (or (and? F) (or? F) (imp? F) (equ? F)))
(define (symbProp? F) (and (symbol? F) (not (symbLog? F))))
(define (atomicFbf? F) (or (symbProp? F) (top? F) (bot? F)))
(define (fbf? F)
  (cond ((atomicFbf? F) 					   #t )
        ((list? F) (cond ((and (= (length F) 2) (neg? (car F)))    (fbf? (cadr F)))
                         ((and (= (length F) 3) (conBin? (car F))) (and (fbf? (cadr F)) (fbf? (caddr F))) )
                         (else #f)))
        (else #f)))
(define (conRac F) (car F))
(define (fils F) (cadr F))
(define (filsG F) (cadr F))
(define (filsD F) (caddr F))
(define (negFbf? F) (and (not (atomicFbf? F)) (neg? (conRac F))))
(define (conjFbf? F) (and (not (atomicFbf? F)) (and? (conRac F))))
(define (disjFbf? F) (and (not (atomicFbf? F)) (or? (conRac F))))
(define (implFbf? F) (and (not (atomicFbf? F)) (imp? (conRac F))))
(define (equiFbf? F) (and (not (atomicFbf? F)) (equ? (conRac F))))


; Q1
(display "\nQ1\n")
(display "F => ") F
(display "G => ") G
(display "H => ") H
(define F1 '(<-> (^ a b) (v (! a) b)))
(define F2 '(v (! (^ a (! b))) (! (-> a b))))
(define F3 '(^ (! (-> a (v a b))) (! (! (^ a (v b (! c))))) ))
(define F4 '(^ (v (! a) (v b d)) (^ (v (! d) c) (^ (v c a) (^ (v (! c) b) (^ (v (! c) (! b)) (v (! b) d) ))))))


; Q2
(display "\nQ2\n")
(display "(fbf? F) => ") (fbf? F)
(display "(fbf? G) => ") (fbf? G)
(display "(fbf? H) => ") (fbf? H)
(display "(fbf? '(! a b)) => ") (fbf? '(! a b))
(display "F1 fbf ? ") (fbf? F1)
(display "F2 fbf ? ") (fbf? F2)
(display "F3 fbf ? ") (fbf? F3)
(display "F4 fbf ? ") (fbf? F4)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; 2 Syntaxe des propositions

;Q3
(display "\nQ3\n")
(define (nbc F)
  (cond ((atomicFbf? F) 0)
        ((negFbf? F) (+ 1 (nbc (fils F))))
        (else (+ 1 (nbc (filsG F)) (nbc (filsD F))))))


(display "nbr de connecteurs de F1 = ") (nbc F1)
(display "nbr de connecteurs de F2 = ") (nbc F2)
(display "nbr de connecteurs de F3 = ") (nbc F3)
(display "nbr de connecteurs de F4 = ") (nbc F4)


;Q4
(display "\nQ4\n")
(define (prof F)
  (cond ((atomicFbf? F) 0)
        ((negFbf? F) (+ 1 (prof (fils F))))
        (else (+ 1 (max (prof (filsG F)) (prof (filsD F)))))))

(display "prof de F1 = ") (prof F1)
(display "prof de F2 = ") (prof F2)
(display "prof de F3 = ") (prof F3)
(display "prof de F4 = ") (prof F4)


;Q5
(display "\nQ5\n")
(define (ensSP F)
  (cond ((or (top? F) (bot? F)) '())
        ((symbProp? F) (set-add '() F))
        ((negFbf? F) (set-union '() (ensSP (fils F))))
        (else (set-union (ensSP (filsG F)) (ensSP (filsD F))))))


(display "Ensemble des symboles de F1 : ") (ensSP F1)
(display "Ensemble des symboles de F2 : ") (ensSP F2)
(display "Ensemble des symboles de F3 : ") (ensSP F3)
(display "Ensemble des symboles de F4 : ") (ensSP F4)


;Q6
(display "\nQ6\n")
(define (aff F)
  (cond ((atomicFbf? F) F)
        ((negFbf? F) (list '! (aff (fils F)) ))
        (else (list (aff (filsG F)) (conRac F) (aff (filsD F))))
              ))


(display "Affichage de F1 : ") (aff F1)
;(display "Affichage de F2 : ") (aff F2)
;(display "Affichage de F3 : ") (aff F3)
;(display "Affichage de F4 : ") (aff F4)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; 3 Semantique

(define I '((a . 0) (b . 1)))


;Q7
(define I1 '((a . 1) (b . 0) (c . 1)))
(define I2 '((a . 0) (b . 0) (c . 0)))
(define I3 '((a . 1) (b . 1) (c . 1)))


;Q8
(display "\nQ8\n")
(define (intSymb s I)
  (cond ((null? I) 0)
        ((eq? s (caar I)) (cdar I))
        (else (intSymb s (cdr I)))))

(display "Valeur de c dans I2 : ") (intSymb 'c I1)
(display "Valeur de c dans I2 : ") (intSymb 'c I2)
(display "Valeur de c dans I2 : ") (intSymb 'c I3)


;Q9
(define (intTop) 1)
(define (intBot) 0)
(define (intNeg v) (if (= v 1) 0 1))
(define (intAnd v1 v2) (* v1 v2))
(define (intOr v1 v2) (if (= v1 1) v1 v2))
(define (intImp v1 v2) (if (= v1 1) v2 1))
(define (intEqu v1 v2) (if (= v1 1) v2 (intNeg v2)))


(display "\nQ9\n")
(display "Valeur de 'Top' : ") (intTop)
(display "Valeur de '0 et 1' : ") (intAnd 0 1)
(display "Valeur de '0 ou 1' : ") (intOr 0 1)
(display "Valeur de '0 -> 1' : ") (intImp 0 1)
(display "Valeur de '0 <-> 0' : ") (intEqu 0 0)


;Q10 
(define  (valV F I)
    (cond ((symbProp? F) (intSymb F I))
        ((negFbf? F) (intNeg (valV (fils F) I)))
        ((conjFbf? F) (intAnd (valV (filsG F) I) (valV (filsD F) I)))
        ((disjFbf? F) (intOr (valV (filsG F) I) (valV (filsD F) I)))
        ((implFbf? F) (intImp (valV (filsG F) I) (valV (filsD F) I)))
        ((equiFbf? F) (intEqu (valV (filsG F) I) (valV (filsD F) I)))
              ))


(define I4 '((a . 0) (b . 0) (c . 1) (d . 1)))

(display "\nQ10\n")
(display "Modèle I : a=") (intSymb 'a I4)
(display "b=") (intSymb 'b I4)
(display "c=") (intSymb 'c I4)
(display "d=") (intSymb 'd I4)

(display "Valeur de F1 dans I : ") (valV F1 I4)
(display "Valeur de F2 dans I : ") (valV F2 I4)
(display "Valeur de F3 dans I : ") (valV F3 I4)
(display "Valeur de F4 dans I : ") (valV F4 I4)


;Q11
(display "\nQ11\n")
(define (modele? I F) (= (valV F I) 1))

(display "I4 modèle de F1 ? ") (modele? I4 F1)
(display "I4 modèle de F2 ? ") (modele? I4 F2)
(display "I4 modèle de F3 ? ") (modele? I4 F3)
(display "I4 modèle de F4 ? ") (modele? I4 F4)

  
;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; 4 Satisfiabilite, Validite


;Q12
(define EI '(((p . 0) (q . 0)) ((p. 0) (q . 1)) ((p . 1) (q . 0)) ((p . 1) (q . 1))))


;Q13
(display "\nQ13\n")
(define (ensInt S)
  (if (set-empty? S) '(())
      (let ((EI (ensInt (set-rest S))))
                 (append (map (lambda (I) (set-add I (cons (set-first S) 0) )) EI)
                         (map (lambda (I) (set-add I (cons (set-first S) 1) )) EI)))))


(display "ensemble des interprétations de F1") (ensInt (ensSP F1))



;Q14
(display "\nQ14\n")
(define (satisfiable? F)
  (ormap (lambda (I) (modele? I F)) (ensInt (ensSP F)) ))

(display "satisfiable F1 ? ") (satisfiable? F1)
(display "satisfiable F2 ? ") (satisfiable? F2)
(display "satisfiable F3 ? ") (satisfiable? F3)
(display "satisfiable F4 ? ") (satisfiable? F4)


;Q15
(display "\nQ15\n")
(define (valide? F)
  (andmap (lambda (I) (modele? I F)) (ensInt (ensSP F)) ))

(display "modèle F1 ? ") (valide? F1)
(display "modèle F2 ? ") (valide? F2)
(display "modèle F3 ? ") (valide? F3)
(display "modèle F4 ? ") (valide? F4)


;Q16
(display "\nQ16\n")
(define (insatisfiable? F) (not (satisfiable? F)))

(display "insatisfiable F1 ? ") (insatisfiable? F1)
(display "insatisfiable F2 ? ") (insatisfiable? F2)
(display "insatisfiable F3 ? ") (insatisfiable? F3)
(display "insatisfiable F4 ? ") (insatisfiable? F4)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; 5 Equivalence, Consequence

;Q17
(display "\nQ17\n")
(define (equivalent1? f1 f2)
  (andmap (lambda (I) (= (valV f1 I) (valV f2 I)))
          (ensInt (set-union (ensSP F1) (ensSP F2)))))

(display "F1 équivalent à F2 (methode 1) ? ") (equivalent1? F1 F1)


;Q17 bis
(define (equivalent2? f1 f2)
  (andmap (lambda (i) (if (= (intEqu (valV f1 i) (valV f2 i)) 1) #t #f))
       (ensInt (set-union (ensSP f1) (ensSP f2)))))
(display "F1 équivalent à F2 (methode 2) ? ") (equivalent2? F1 F1)


;Q18
(display "\nQ18\n")
(define (consequence2? f1 f2)
  (andmap (lambda (I) (if (= (intImp (valV f1 I) (valV f2 I)) 1) #t #f))
          (ensInt (set-union (ensSP f1) (ensSP f2)))))


(display "(a ^ b) conséquence logique de a ? ") (consequence2? 'a '(^ a b))


;Q19
(display "\nQ19\n")
(define (ensSPallFbf2 Fs)
  (cond
    ((null? Fs) '())
    (else (set-union (ensSP (car Fs)) (ensSPallFbf2 (cdr Fs))))))

(define (ensSPallFbf Fs) (ensInt (ensSPallFbf2 Fs)))


(ensSPallFbf (list F1 F2 F3))



;Q20
(define (modeleCommun? I Fs) (andmap (lambda (F) (modele? I F)) Fs))

(display "est-ce que F1 et F2 ont un modèle commun ? ") (modeleCommun? I1 (list F1 F2))



;Q21
(display "\nQ21\n")
(define (contradictoire? Fs) (not (ormap (lambda (i) (modeleCommun? i Fs)) (ensSPallFbf (cons F Fs)))))

(display "est-ce que F1 et F4 sont contradictoires ? ") (contradictoire? (list F1 F4))



;Q22
(display "\nQ22\n")
(define (consequence? Fs F)
  (andmap (lambda (I) (if (modeleCommun? I Fs) (= (valV F I) 1) #t)) (ensSPallFbf (cons F Fs))))

(display "est-ce que d est conscéquence logique de {a^b,a,b->d} ? ")(consequence? '((^ a b) a (-> b d)) 'd)





;Q23
(display "\nQ23\n")
(define (conjonction EF) ; EF est une ensemble de fbf
  (cond  ((set-empty? EF) 'Top)
         ((set-empty? (set-rest EF)) (set-first EF))
         (else (list '^ (set-first EF) (conjonction (set-rest EF))))))

(define (consequenceV? Fs F)
  (andmap (lambda (I) (= 1 (intImp (valV (conjonction Fs) I) (valV F I)))) (ensSPallFbf (cons F Fs))))

(display "est-ce que !d est conscéquence logique de {a^b,!a,b->d} ? ") (consequenceV? '((^ a b) a (-> b d)) '(! d))


;Q24
(display "\nQ24\n")
(define (consequenceI? Fs F)
  (andmap (lambda (I) (= 1 (valV (conjonction (cons (list '! F) Fs)) I))) (ensSPallFbf (cons F Fs))))

(display "est-ce que !d est conscéquence logique de {a^b,!a,b->d} ? ") (consequenceI? '((^ a b) a (-> b d)) '(! d))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; 6 Mise sous forme conjonctive


;Q25
(display "\nQ25\n")
(define (oteEqu F)
  (cond ((atomicFbf? F) F)
        ((negFbf? F) (append '(!) (list (oteEqu (fils F)))))
        ((not (equiFbf? F)) (append (list (conRac F)) (list (oteEqu (filsG F)) (oteEqu (filsD F)))))
        (else (append '(^) (append (list (append '(->) (list (oteEqu (filsG F)) (oteEqu (filsD F)))))
                                   (list (append '(->) (list (oteEqu (filsD F)) (oteEqu (filsG F))))))))))

(display "(<-> Bot (! a)) sans les <-> \n")
(oteEqu '(! (<-> Bot (! a))))



;Q26
(display "\nQ26\n")
(define (oteImp F)
  (cond ((atomicFbf? F) F)
        ((negFbf? F) (append '(!) (list (oteImp (fils F)))))
        ((not (implFbf? F)) (append (list (conRac F)) (list (oteImp (filsG F)) (oteImp (filsD F)))))
        (else (append '(v) (append (list (append '(!) (list (oteImp (filsG F)))))
                                   (list (oteImp (filsD F))))))))


(display "(-> Top (<-> Bot a)) sans les -> \n")
(oteImp '(-> Top (<-> Bot a)))



;Q27
(display "\nQ27\n")

(define (oteCste F)
  (cond ((or (top? F) (bot? F)) (if (top? F)
                                    '(v (! p) p)
                                    '(^ (! p) p)))
        ((atomicFbf? F) F)
        ((negFbf? F) (append '(!) (list (oteCste (fils F)))))
        (else (list (conRac F) (oteCste (filsG F)) (oteCste (filsD F))))))




(display "(-> Top (<-> Bot a)) sans les Bot/Top \n")
(oteCste '(-> Top (<-> Bot a)))



;Q28
(display "\nQ28\n")
(define (redNeg F)
  (cond ((symbProp? F) F)
        ((not (negFbf? F)) (list (conRac F) (redNeg (filsG F)) (redNeg (filsD F))))
        (else
         (cond ((symbProp? (fils F)) F)
               ((negFbf? (fils F)) (redNeg (fils (fils F))))
               ((conjFbf? (fils F)) (list 'v (redNeg (list '! (filsG (fils F))))
                                          (redNeg (list '! (filsD (fils F))))))
               (else (list '^ (redNeg (list '! (filsG (fils F))))
                           (redNeg (list '! (filsD (fils F))))))))))


(display "(! (! (^ a b))) avec les ! au plus bas \n")
(redNeg '(! (! (! (v a b)))))




;Q29
(display "\nQ29\n")
(define (distOu F)
  (cond ((symbProp? F) F)
        ((negFbf? F) (list '! (distOu (fils F))))
        ((conjFbf? F) (list '^ (distOu (filsG F)) (distOu (filsD F))))
        (else
         (let ((Fg (distOu (filsG F)))
               (Fd (distOu (filsD F))))  
           (cond ((conjFbf? Fg)	(list '^ (distOu (list 'v (filsG Fg) Fd)) (distOu (list 'v (filsD Fg) Fd))))
                 ((conjFbf? Fd)	(list '^ (distOu (list 'v Fg (filsG Fd))) (distOu (list 'v Fg (filsD Fd)))))
                 (else (list 'v Fg Fd)))))))



(display "(v (^ a b) (^ c d))) sous forme conjonctive \n")
(distOu '(v (^ a b) (v c d)))



;Q30
(display "\nQ29\n")
(define (formeConj F) (distOu (redNeg (oteCste (oteImp (oteEqu F))))))

(display "(! (-> Bot (! b))) sous forme conjonctive \n")
(formeConj '(! (-> Bot (! b))))



  
;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; 7 Mise sous forme clausale

; Exemple de clause et forme clausale
(define exClause '( p (! r) t)) 
(define exFormeClausale '( (p (! p))  (p q (! r))  ((! r) s)  (p (! r) t)  (p ( ! r))  (r (! t))  (s t) (p (! s))   ((! p ) (! s))))

; Fonction permettant de tester si une fbf est un littéral et d'obtenir le littéral opposé d'un littéral
(define (litteral? F) (or (symbProp? F) (and (negFbf? F) (symbProp? (fils F)))))
(define (oppose L) (if (symbProp? L) (list '! L) (fils L)))
  
; Fonctions permettant de manipuler des ensembles d'ensembles
(define (setSet-member? EC C)
  (cond ((set-empty? EC) #f)
        ((set=? (set-first EC) C) #t)
        (else (setSet-member? (set-rest EC) C))))

(define (setSet-add EC C)
  (cond ((set-empty? EC) (list C))
        ((set=? (set-first EC) C) EC)
        (else (set-add (setSet-add (set-rest EC) C) (set-first EC)))))

(define (setSet-union EC1 EC2)
  (if (set-empty? EC2) EC1
      (setSet-union (setSet-add EC1 (set-first EC2)) (set-rest EC2))))

;Q31
(display "\nQuestion 31\n")
(define (transClause F) (if (litteral? F) (list F) (set-union (transClause (filsG F)) (transClause (filsD F)))))

;(transClause '(v (^ a b)))
;(transClause '(v (v a b) (v c d)))

;Q32
(display "\nQuestion 32\n")
(define (transEnsClause F)
  (cond ((null? F) '())
        ((not (conjFbf? F)) (list (transClause F)))
        (else (setSet-union (transEnsClause (filsG F)) (transEnsClause (filsD F))))))

(transEnsClause '(^ (^ (v a (! b)) (! a)) (v b d)))

;Q33
(display "\nQuestion 33\n")
(define (formeClausale F)
  (cond ((null? F) '())
        ((not (conjFbf? F)) (transEnsClause (formeConj F)))
        (else (transEnsClause F)))) 

(formeClausale '(-> a b))


;Q34
(display "\nQuestion 34\n")
(define (tautologie C)
  (cond ((null? C) #f)
        (else (let [(e (oppose (car C)))]
                      (if (ormap (lambda (l) (equal? l e)) C)
                  #t
                  (tautologie (cdr C)))))))

(display "\ntestons si la clause '(a b) est une tautologie : ")
(tautologie '(a b))

(display "\ntestons si la clause '((! a) a) est une tautologie : ")
(tautologie '((! a) a))

;Q35
(display "\nQuestion 35\n")
(define (subsume C1 C2)
  (cond ((null? C1) #t)
        (else (let [(e (car C1))]
                (if (ormap (lambda (l) (equal? l e)) C2)
                    (subsume (cdr C1) C2)
                    #f)))))

(display "\nsubsume de {a,b,c} et {a,b,c,d,e} : ")
(subsume '(a b c) '(a b c d e))

(display "\nsubsume de {a,b,c,d} et {a,b,c} : ")
(subsume '(a b c d) '(a b c))

;Q36

(display "\nQuestion 36\n")
(define (simplifier F)
  (filter (lambda (C) (and (not (tautologie C))
                           (not (ormap (lambda (c) (and (not (eq? c C))
                                                        (subsume c C))) F)))) F))
(simplifier '((a b c) (a (! a)) (b c)))
;;;;;;;;;;;;;;;
; 8  Resolution

;Q37
(display "\nQuestion 37\n")
(define (resolvable C1 C2)
   (= (set-count (filter (lambda (e) (set-member? C2 (oppose e))) C1)) 1))

(display "\nresolvable de {a,b,c,d} et {!a,b,c} : ")
(resolvable '(a b c d) '((! a) b c))


;Q38
(display "\nQuestion 38\n")
(define trouverpair (lambda (C)
                      (letrec
                          ((aux (lambda (clause temp)
                                  (if (null? clause)
                                      temp
                                      (if (set-member? clause (oppose (car clause)))
                                          (aux (cdr clause) (cons (car clause) temp))
                                          (aux (cdr clause) temp))
                                  ))))
                        (aux C '()))))


 (define (resolvante C1 C2)
      (remove (oppose (car (trouverpair (set-union C1 C2)))) (remove (car (trouverpair (set-union C1 C2))) (set-union C1 C2))))

(display "\nresolvante de {a,!b,c} et {!a,!b,d} : ")
(resolvante '(a (! b) c) '((! a) (! b) d))


;Q39
(define (ClauseVideParResolution FC)
    (letrec ((f3 (lambda (C EN P) (if (null? EN) P
                                      (if (resolvable C (car EN))
                                          (if (setSet-member? (setSet-union EN P) (resolvante C (car EN)))
                                              (f3 C (cdr EN) P)
                                              (f3 C (cdr EN) (setSet-add P (resolvante C (car EN)))))
                                          (f3 C (cdr EN) P)))))
                 
             (f2 (lambda (EN N P) (if (null? N) P
                                     (f2 EN (cdr N) (f3 (car N) EN P)))))
             
             (f1 (lambda (E N) (if (null? N) #f
                                   (if (setSet-member? N '()) #t
                                       (f1 (setSet-union E N) (f2 (setSet-union E N) N '())))))))
      (f1 '() FC)))



;Q40
(define (satisfiableResol F)
  (not(ClauseVideParResolution (simplifier(formeClausale F))))
  )

(define (valideResol F)
  (ClauseVideParResolution (simplifier(formeClausale (redNeg (list '! (formeConj F))))))
  )

(define (consequenceResol F C)
    (ClauseVideParResolution (simplifier(formeClausale(conjonction (list F (redNeg (list'! (formeConj C))))))))
  )


;;;;;;;;;;;;;;;
; 9 Application

;Q41


;;;;;;;;;;;;;;;
; 10 Evaluation


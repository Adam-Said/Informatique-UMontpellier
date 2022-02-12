from matplotlib import pyplot as plt
from matplotlib import collections  as mc

### Dessins ###

def __points(ax, Points, color, markersize):
    ax.plot(*zip(*Points),marker='.',markersize=markersize,linestyle='',color=color)

def __aretes(ax, Points, Adj, color, markersize, lw):
    liste = []
    for s in Adj:
        for v in Adj[s]:
            liste.append([Points[s], Points[v]])
    lc = mc.LineCollection(liste, linewidth=lw, color=color)
    ax.add_collection(lc)
    ax.autoscale()

def __parcours(ax, Points, Parcours, Adj, color1, color2, markersize):
    n = len(Points)
    liste1 = []
    liste2 = []
    for i in range(len(Parcours)):
        if len(Adj) == 0 or Parcours[(i+1)%n] in Adj[Parcours[i]]: 
            liste1.append([Points[Parcours[i]], Points[Parcours[(i+1)%n]]])
        else:
            liste2.append([Points[Parcours[i]], Points[Parcours[(i+1)%n]]])
    lc1 = mc.LineCollection(liste1, linewidth=1, color=color1)
    lc2 = mc.LineCollection(liste2, linewidth=1, color=color2)
    ax.add_collection(lc1)
    ax.add_collection(lc2)
    ax.autoscale()

    if len(Parcours) < 100:
        for s in range(len(Parcours)):
            ax.annotate(str(s+1), Points[Parcours[s]])

def dessinPoints(Points, color="C0",markersize=5):
    fig, ax = plt.subplots()
    __points(ax, Points, color, markersize)
    plt.axis('equal')
    plt.show()

def dessinGraphe(Points, Adj, color="C0",markersize=5):
    fig, ax = plt.subplots()
    __points(ax, Points, color, markersize)
    __aretes(ax, Points, Adj, color, markersize, 1)
    plt.axis('equal')
    plt.show()

def dessinParcours(Points, Parcours, Adj = {}, color1="C0", color2="C1", markersize=5):
    n = len(Points)
    assert sorted(Parcours) == list(range(n)), "le parcours ne visite pas chaque sommet une fois et une seule"

    fig, ax = plt.subplots()
    __points(ax, Points, color1, markersize)
    if len(Adj) > 0: __aretes(ax, Points, Adj, 'gray', markersize, .5)
    __parcours(ax, Points, Parcours, Adj, color1, color2, markersize)

    plt.axis('equal')
    plt.show()



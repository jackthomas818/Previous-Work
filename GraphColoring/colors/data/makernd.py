#!/usr/bin/python

from random import randint
from copy import deepcopy
import sys

N = int(sys.argv[1])

E = randint(N-1, N*(N-1)/2)

def makeone():
    a = [[0] * N for _ in range(N)]
    nE = 0
    while nE < E:
        i = randint(0, N-1)
        j = randint(0, N-1)
        if i == j or a[i][j] == 1:
            continue
        a[i][j] = 1
        a[j][i] = 1
        nE += 1
    return a

def output(a):
    print N
    for _a in a:
        print " ".join(map(str, (i for i in range(N) if _a[i])))

def isconnected(a):
    b = deepcopy(a)
    for i in range(N): 
        for j in range(N): 
            for k in range(N): 
                if b[i][k] == 1 and b[k][j] == 1:
                    b[i][j] = 1
                    b[j][i] = 1

    return sum(map(sum,b)) == N * N

while True:
    G = makeone()
    if not isconnected(G):
        continue

    output(G)
    break


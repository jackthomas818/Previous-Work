#!/usr/bin/python

#
# TLE solution for computing chromatic number
#
# @author <godmar@gmail.com>
#
def R(): return map(int, raw_input().split())

n = R()[0]
adj = [R() for i in range(n)]

for i in range(n):
    assert all(i in adj[j] for j in adj[i])

# https://en.wikipedia.org/wiki/Brooks%27_theorem
maxColors = max(map(len, adj)) + 1
for k in range(2, maxColors + 1):
    allcolors = set(range(k))

    def color(C):
        v = next((i for i in range(n) if C[i] == None), -1)
        if v == -1:
            return True

        colorsleft = allcolors - set(C[vv] for vv in adj[v] if C[vv] != None)
        for c in colorsleft:
            C[v] = c
            if color(C):
                return True
            C[v] = None

        return False

    #
    if color([None] * n):
        break

print k

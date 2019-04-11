#!/usr/bin/python

#
# Sample solution for computing the chromatic number
# Computing the chromatic number for general graphs is
# NP-complete (no efficient algorithm is known).
#
# However, backtracking works for small graphs such as
# those in this problem.  Below, we simply try all possible
# assignments of colors to vertices.  Since we can fix
# the colors of two connected vertices, we have at most
# 9! = 362,880 possibilities to try when testing if a
# graph can be colored using k colors.
#
# We also need to find the smallest k. This could be done
# using a binary search, but again for this small of a
# problem size one can simply count up from 2 until we 
# find the smallest k that allows a successful coloring.
#
# Aside: Brook's theorem provides an upper bound on the
# chromatic number.
#
# @author <godmar@gmail.com>
#
def R(): return map(int, raw_input().split())

n = R()[0]
adj = [R() for i in range(n)]

# check that input is consistent; judge only
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

    # fix two colors along an edge arbitrarily
    initial = [None] * n
    initial[0] = 0
    initial[adj[0][0]] = 1
    if color(initial):
        break

print k

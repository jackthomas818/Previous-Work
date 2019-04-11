#!/usr/bin/python

#
# input validation for computing chromatic number
#
# @author <godmar@gmail.com>
#
def R(): return map(int, raw_input().split())

n = R()[0]
adj = [R() for i in range(n)]

for i in range(n):
    assert len(adj[i]) > 0
    assert all(i in adj[j] for j in adj[i])
    assert all(0 <= j < n for j in adj[i])

bfs = list()
bfs.append(0)
seen = set()
seen.add(0)
while len(bfs) > 0:
    u = bfs.pop()
    for v in adj[u]:
        if v not in seen:
            seen.add(v)
            bfs.append(v)

assert len(seen) == n

import sys
sys.exit(42)

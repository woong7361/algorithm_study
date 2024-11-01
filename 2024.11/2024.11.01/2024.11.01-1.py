# 백준 15652 N과M (4) https://www.acmicpc.net/problem/15652
import collections
import pprint
import sys

sys.setrecursionlimit(6000000)

INF = 2147483647


def input(): return sys.stdin.readline().rstrip()


def splitInput(): return list(map(int, input().split(" ")))


N, M = splitInput()

# dfs로 출력하는게 좋을듯


def dfs(result):
    if len(result) == M:
        print(" ".join(map(str,result)))
        return

    for i in range(result[-1], N+1):
        result.append(i)
        dfs(result)
        result.pop()

for i in range(1, N+1):
    dfs([i])


# 백준 15666 N과M (12) https://www.acmicpc.net/problem/15666
import collections
import pprint
import sys

sys.setrecursionlimit(6000000)

INF = 2147483647


def input(): return sys.stdin.readline().rstrip()


def splitInput(): return list(map(int, input().split(" ")))


N, M = splitInput()

numbers = splitInput()

sortedSet = sorted(list(set(numbers)))

def dfs(result):
    if len(result) == M:
        print(" ".join(map(str, result)))
        return

    # 112(re)  3(nu)
    for number in sortedSet:
        if number < result[-1]:
            continue

        result.append(number)
        dfs(result)
        result.pop()


for start in sortedSet:
    dfs([start])



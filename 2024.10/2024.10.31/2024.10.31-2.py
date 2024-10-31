# 백준 16928 토마토 https://www.acmicpc.net/problem/16928
import collections
import pprint
import sys

sys.setrecursionlimit(6000000)

INF = 2147483647


def input(): return sys.stdin.readline().rstrip()


def splitInput(): return list(map(int, input().split(" ")))


M, N = splitInput()

move = {}
for _ in range(0, M+N):
    start, end = splitInput()
    move[start] = end

visit = [0] * 101
queue = collections.deque([1])

while queue:
    current = queue.popleft()

    for dice in range(1, 7):
        next = current + dice
        if next > 100:
            continue

        if next in move.keys():
            next = move[next]

        if visit[next] == 0:
            visit[next] = visit[current]+1
            queue.append(next)

print(visit[100])










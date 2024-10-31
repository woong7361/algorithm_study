# 백준 14940 https://www.acmicpc.net/problem/30804

import pprint
import sys

sys.setrecursionlimit(6000000)

INF = 2147483647

def input(): return sys.stdin.readline().rstrip()
def splitInput(): return list(map(int, input().split(" ")))

N, M = splitInput()

board = []
for _ in range(0, N):
    board.append(splitInput())

start = None
for row in range(0, N):
    for col in range(0, M):
        if board[row][col] == 1:
            board[row][col] = INF
        if board[row][col] == 2:
            start = (row, col)

# dfs로 가면서 가장 거리 갱신
def dfs(row, col, dist):
    # 보드를 벗어나면 끝
    if row < 0 or row >= N or col < 0 or col >= M:
        return
    # 지나갈 수 없는 곳이면 끝 (0)
    if board[row][col] == 0:
        return
    # 방문했는데 최솟값 갱신이 안되면 끝
    if min(board[row][col], dist) == board[row][col]:
        return

    board[row][col] = dist

    dfs(row+1, col, dist+1)
    dfs(row-1, col, dist+1)
    dfs(row, col+1, dist+1)
    dfs(row, col-1, dist+1)


dfs(start[0], start[1], 0)

for row in range(0, N):
    for col in range(0, M):
        if board[row][col] == INF:
            board[row][col] = -1

for i in range(0, N):
    print(board[i])

# 백준 7576 토마토 https://www.acmicpc.net/problem/7576
import collections
import pprint
import sys

sys.setrecursionlimit(6000000)

INF = 2147483647


def input(): return sys.stdin.readline().rstrip()


def splitInput(): return list(map(int, input().split(" ")))


M, N = splitInput()

board = []
for _ in range(0, N):
    board.append(splitInput())

matureTomatos = collections.deque()
for i in range(0, N):
    for j in range(0, M):
        if board[i][j] == 1:
            matureTomatos.append((i, j))

dxy = [(1, 0), (-1, 0), (0, 1), (0, -1)]
while matureTomatos:
    row, col = matureTomatos.popleft()

    for dy, dx in dxy:
        nextRow = row + dy
        nextCol = col + dx
        if nextRow < 0 or nextRow >= N or nextCol < 0 or nextCol >= M:
            continue
        if board[nextRow][nextCol] == 0:
            board[nextRow][nextCol] = board[row][col] + 1
            matureTomatos.append((nextRow, nextCol))


result = -1
flag = 0
for i in range(0, N):
    for j in range(0, M):
        if board[i][j] == 0:
            flag = 1
            break

        result = max(result, board[i][j])

if flag:
    print(-1)
else:
    print(result - 1)


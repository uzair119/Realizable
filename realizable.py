# -*- coding: utf-8 -*-
"""
Created on Mon Jun  3 16:12:14 2019

@author: Uzair
"""

class Node:

    def __init__(self, val):

        self.left = None
        self.right = None
        self.prev = None
        self.val = val



leaves = []
def realizable(A,T):
    S = 0
    for i in A:
        S += i
    rows,cols = (len(A),2*S+1)
    #print(S)
    P = [[False for i in range(cols)] for j in range(rows)]
    P[0][S] = True
    #print(P)
    for i in range(1,len(P)):
        for j in range(-S,S+1):
            if j-A[i] <= S and j-A[i] >= -S and P[i-1][j-A[i] + S]:
                #print(i,j)
                P[i][j + S] = True
            elif j+A[i] <= S and j+A[i] >= -S and P[i-1][j+A[i] + S]:
                #print(i,j)
                P[i][j + S] = True
    return P[len(A)-1][T+S]


def showone(A,T):
    S = 0
    for i in A:
        S += i
    rows,cols = (len(A),2*S+1)
    #print(S)
    P = [[False for i in range(cols)] for j in range(rows)]
    P[0][S] = True
    #print(P)
    for i in range(1,len(P)):
        for j in range(-S,S+1):
            if j-A[i] <= S and j-A[i] >= -S and P[i-1][j-A[i] + S]:
                #print(i,j)
                P[i][j + S] = True
            elif j+A[i] <= S and j+A[i] >= -S and P[i-1][j+A[i] + S]:
                #print(i,j)
                P[i][j + S] = True
    if P[len(A)-1][T+S]:
        j = T
        stack = []
        for i in range(len(A)-1,0,-1):
            if j-A[i] <= S and j-A[i] >= -S and P[i-1][j-A[i] + S]:
                stack.append('-'+str(A[i]))
                j += A[i]
            else:
                stack.append('+'+str(A[i]))
                j -= A[i]
        stri = "Solution: "
        while len(stack) > 0:
            stri += stack.pop()
        print(stri + " = " + str(T))
    else:
        print("No solution")
        


def showall(A,T):
    S = 0
    for i in A:
        S += i
    rows,cols = (len(A),2*S+1)
    #print(S)
    P = [[False for i in range(cols)] for j in range(rows)]
    P[0][S] = True
    #print(P)
    for i in range(1,len(P)):
        for j in range(-S,S+1):
            if j-A[i] <= S and j-A[i] >= -S and P[i-1][j-A[i] + S]:
                #print(i,j)
                P[i][j + S] = True
            elif j+A[i] <= S and j+A[i] >= -S and P[i-1][j+A[i] + S]:
                #print(i,j)
                P[i][j + S] = True
    if P[len(A)-1][T+S]:
        root = Node(T)
        buildTree(A,len(A)-1,P,T,S,root)
        printSolutions(T)
    else:
        print("Number of solutions = 0")
    


def buildTree(A,i,P,j,S,root):
    if i == 0:
        leaves.append(root)
        return
    if j+A[i] <= S and j+A[i] >= -S and P[i-1][j+A[i] + S]:
        n = Node(j+A[i])
        root.right = n
        n.prev = root
        buildTree(A,i-1,P,n.val,S,n)
    if j-A[i] <= S and j-A[i] >= -S and P[i-1][j-A[i] + S]:
        n = Node(j-A[i])
        root.left = n
        n.prev = root
        buildTree(A,i-1,P,n.val,S,n)


def printSolutions(T):
    print("Number of solutions: " + str(len(leaves)))
    j = 0
    for i in leaves:
        j+=1
        stri = ""
        n = i
        while n.prev != None:
            stri += "+" + str(n.prev.val-n.val) if n.prev.val > n.val else str(n.prev.val-n.val)
            n = n.prev
        print("Sol " + str(j) + " " + stri + " = " + str(T))

def main():
    n = int(input("n:"))
    A = list(map(int,input("Space separated numbers: ").split()))
    A = [0] + A
    T = int(input("T: "))
    #print(A)
    print("n = " + str(n))
    print()
    print("Part 1: Realizability check")
    if realizable(A,T):
        print("The value " + str(T) + " is realizable")
    else:
        print("The value " + str(T) + " is not realizable")
    print()
    print("Part 2: One solution")
    showone(A,T)
    print()
    print("Part 3: All solutions")
    showall(A,T)
    
main()

    
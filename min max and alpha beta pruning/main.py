import math


def depth(L):
    return math.log(len(L), 2)


def Test(L):
    dep = depth(L)
    if dep % 2 == 0:
        return L
    else:
        return [element * -1 for element in L]


def minimax(depth, nodeIndex, maxTurn, L):
    if depth == 0:
        return L[nodeIndex]

    if maxTurn:
        val1 = minimax(depth - 1, nodeIndex * 2, False, L)
        val2 = minimax(depth - 1, nodeIndex * 2 + 1, False, L)
        val = max(val1, val2)
        return val

    else:
        val1 = minimax(depth - 1, nodeIndex * 2, True, L)
        val2 = minimax(depth - 1, nodeIndex * 2 + 1, True, L)
        val = min(val1, val2)
        return val


def Negamax(depth, nodeIndex, L):
    if depth == 0:
        return L[nodeIndex]

    val1 = Negamax(depth - 1, nodeIndex * 2, L)
    val2 = Negamax(depth - 1, nodeIndex * 2 + 1, L)
    val = max(-val1, -val2)
    return val


MAX, MIN = math.inf, -math.inf


def ALPHABETA(depth, nodeIndex,
              L, alpha, beta):
    best = MIN

    if depth == 0:
        return L[nodeIndex]
    i = 0
    while i < 2:
        val = ALPHABETA(depth - 1, nodeIndex * 2 + i,
                        L, alpha, beta)
        best = max(best, -val)
        beta = max(beta, -best)
        alpha = max(alpha, -beta)
        i += 1
        if beta < best or best < alpha:
            break

    return best


if __name__ == '__main__':
    L1 = [15, 6, 3, 12, 42, 45, 42, 39, -9, 66, 27, 36, 33, 30, 61, 60]
    # L = [9, 15, 18, 6, 3, 12, 21, 24]

    dep = depth(L1)
    print("Valeur optimal (MINMAX) :", minimax(dep, 0, True, L1))
    L1 = Test(L1)
    print("Valeur optimal (ALPHABETA) :", ALPHABETA(dep, 0, L1, MIN, MAX))

    print("Valeur optimal (NEGAMAX) :", Negamax(dep, 0, L1))

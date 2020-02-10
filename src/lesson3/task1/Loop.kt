@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 1
    var m = n / 10
    while (m > 0) {
        count++
        m = m / 10
    }
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n < 3) return 1
    var fn = 1
    var fn1 = 1
    var f = fn + fn1
    for (i in 4..n + 1) {
        f = fn + fn1
        fn = fn1
        fn1 = f
    }
    return f
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    if (m == n) return m
    //if ((n % 2 == 0) && (m % 2 == 0)) return 2
    val minv = if (n < m) n else m
    val maxv = if (n < m) m else n
    if (maxv % minv == 0) return maxv
    var result = m * n
    val simplemul = minv / 2
    var divider = 2
    while (divider < simplemul) {
        if (isPrime(divider)) {
            if ((n % divider == 0) && (m % divider == 0)) result /= divider
        }
        divider++
    }
    return result
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    if (n % 2 == 0) return 2
    if (isPrime(n)) return n
    for (i in 3..n/2 step 2){
        if (!isPrime(i)) continue
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    if (m == n) return false
    if ((n % 2 == 0) && (m % 2 == 0)) return false
    val minv = if (n < m) n else m
    val maxv = if (n < m) m else n
    if (maxv % minv == 0) return false
    for (i in 3..sqrt(minv.toDouble()).toInt() step 2) {
        if ((n % i == 0) && (m % i == 0)) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val bi = sqrt(m.toDouble()).toInt()
    val ei = sqrt(n.toDouble()).toInt() + 1
    for (i in bi..ei) {
        if ((i * i <= n) && (i * i >= m)) return true
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var step = 0
    var next = x
    while (next > 1) {
        if (next % 2 == 0) next /= 2 else next = 3 * next + 1
        step++
    }
    return step
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    val y = x - ((x / 2 / PI).toInt()).toDouble() * 2 * PI
    var result = y
    if (y < eps) return result
    var step = 3
    var value = -y * y * y / 6.0
    while (abs(value) > eps) {
        result += value
        step += 2
        val nm = y * y / (step * (step - 1)).toDouble()
        value = -value * nm
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    val y = x - ((x / 2 / PI).toInt()).toDouble() * 2 * PI
    var result = 1.0
    if (y < eps) return result
    var step = 2
    var value = -y * y / 2.0
    while (abs(value) > eps) {
        result += value
        step += 2
        val nm = y * y / (step * (step - 1)).toDouble()
        value = -value * nm
    }
    return result
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var result = 0
    var rev = n
    while (rev > 0) {
        result = result * 10 + (rev % 10)
        rev /= 10
    }
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var m = n
    val fd = m % 10
    m /= 10
    while (m > 0) {
        if (m % 10 != fd) return true
        m /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var baseSq = 1
    var m = n
    var digit = 0
    while (true) {
        var sq = baseSq * baseSq
        baseSq++
        val digits = digitNumber(sq)
        if (digits < m) {
            m -= digits
            continue
        }
        m = digits - m + 1
        while (m > 0 && sq > 0) {
            digit = sq % 10
            sq /= 10
            m--
        }
        return digit
    }
    return 0
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    if (n < 3) return 1
    var fn = 1
    var fn1 = 1
    var m = n - 2
    var digit = 0
    while (true) {
        var sq = fn + fn1
        fn = fn1
        fn1 = sq
        val digits = digitNumber(sq)
        if (digits < m) {
            m -= digits
            continue
        }
        m = digits - m + 1
        while (m > 0 && sq > 0) {
            digit = sq % 10
            sq /= 10
            m--
        }
        return digit
    }
}

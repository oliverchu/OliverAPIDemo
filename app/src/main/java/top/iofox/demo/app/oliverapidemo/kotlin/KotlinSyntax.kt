package top.iofox.demo.app.oliverapidemo.kotlin

/**
 * Created by [Oliver Chu] on 2018/11/26 12:04
 */
class KotlinSyntax {
    fun ifSyntax(a: Int, b: Int): Int {
        val max = if (a > b) a else b
        //or
        val min = if (a < b) {
            print("Do something")
            a
        } else {
            print("Do something else")
            b
        }
        return max
    }


    fun whenSyntax(x: String) {
        when (x) {
            "a" -> print("a")
            "b" -> print("b")
            "c", "d" -> print("cd")
            in "a".."b" -> print("Oh,kotlin is smart!")
            !in "abc".."xyz" -> print("not in range")

            else -> {
                print("x belongs to nothing")
            }
        }

    }


    fun whenIsSyntax(x: Any) {
        when (x) {
            is String -> print(x.startsWith("x"))
            else -> false
        }
        if (x is String) {
            when {
                x.isNotBlank() -> print("Don't have blank")
                else -> print("Well done")
            }
        }
        val items = setOf<String>("1", "2", "23", "34")
        when {
            "1" in items -> print("1")
            "23" in items -> print("23")
        }
    }

    fun forSyntax() {
        val arrays = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        for (i in arrays.indices) { //序号
            print(arrays[i])
        }
        for ((index, value) in arrays.withIndex()) {
            println("The element at $index is $value")
        }

        for (x in arrays) {
            println(x)
        }
        arrays.forEach lit@{
            if (it == 5) {
                return@lit
            }
            print(it)
        }

        arrays.forEach {
            if (it == 5) {
                return@forEach
            }
        }

        arrays.forEach {
            fun(value: Int) {
                if (value == 5) {
                    return

                }
            }
        }

    }


    //do while / while loop is likely to java

    fun breakSytnax() {
        loop@ for (i in 1..10)
            for (j in 1..10)
                if (i == 5 && j == 5) {
                    break@loop
                }
    }


}
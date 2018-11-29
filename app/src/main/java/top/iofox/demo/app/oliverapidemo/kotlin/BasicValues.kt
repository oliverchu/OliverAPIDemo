package top.iofox.demo.app.oliverapidemo.kotlin

/**
 * Created by [Oliver Chu] on 2018/11/26 11:37
 */
class BasicValues() {
    init {
        val oneMillion = 11_999_00L
        val hex = 0xff_ba_00_00
        val bytes = 0b1100_1010_1000_1111


    }

    private var valDouble: Double = 0.0
    private var valFloat: Float = 0.0f
    private var valLong: Long = 0
    private var valInt: Int = 0
    private var valShort: Short = 0
    private var valByte: Byte = 0


    fun compare() {
        println(valDouble === valDouble) //true 值相等，对象地址相等
        val boxedDouble1: Double? = valDouble
        val boxedDouble2: Double? = valDouble
        println(boxedDouble1 === boxedDouble2) // false 值相等，地址不同
        println(boxedDouble1 == boxedDouble2) //true 值相同
        var i: Short = valLong.toInt().toFloat().toByte().toLong().toChar().toShort()
        valLong.toBigInteger().toBigDecimal()
        val l = 12L + 3  //l 类型是Long
        //对于 Int 和 Long
        1.shl(1)// <<
        1.shr(2)// >>
        1.ushr(1)//无符号右移 >>>
        1.and(2)  //and
        1.or(0) //or
        1.xor(2) //xor
        1.inv() //反向!

    }

    fun string(c: Char) {
        if (c == 'c') {
            //do something
            print(c.toInt())
        }
        for (s in "I am Oliver") {
            print("$s,")
        }
        val multiLine = """
            多行文字
            格>式化
        """.trimIndent().trimMargin(">")
        println(multiLine)
        val x = "121"
        val y = "$x.length = ${x.length}"


    }

    fun array() {
        val a = arrayOf(1, 2, 3, 4, 5, 6)
        val b = Array(3) { i -> (i * 2) } // {0,2,4}
        print("${a[1]} and ${b[0]}")
        val x: IntArray = intArrayOf(1, 23, 4)
        val y: BooleanArray = booleanArrayOf(true, false, true, false)
        y[0] = y[1].or(y[2])


    }


}
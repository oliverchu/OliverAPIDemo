package top.iofox.demo.app.oliverapidemo.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_kotlin.*
import top.iofox.demo.app.oliverapidemo.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by [Oliver Chu] on 2018/11/26 10:53
 */
class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        val text = "test"
        btnClick.setOnClickListener {
            btnClick.text = "xad:$text"
        }

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        val format = simpleDateFormat.format(Date())
        println(format)
        println(ab(1, 2))


    }

    private fun ab(a: Int, b: Int): Int? {
        return a + b
    }

    public fun amulb(a: Int, b: Int): Int = a * b

    protected fun nonRuturn(): Unit {
        print(nonRuturn(1, 2, 3, 4, 5, 6))
        val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
        println(sumLambda(1, 2))
        val a = 0
        //a++ !val 类似final ，不可修改
        var b = 0
        b++
        println("${"dwad".replace("wad", "oliver")}")

    }

    fun nonRuturn(vararg a: Int) {
        print("$a,")
    }

    fun checkNullable(title: String) {
        //类型后面加?表示可为空
        var age: String? = "25"
        //抛出空指针异常
        val ages = title!!.toInt()
        //不做处理返回 null
        val ages1 = age?.toInt()
        //age为空返回-1
        val ages2 = age?.toInt() ?: -1
        if (age == null) {
            //ages is always true
            print("age is null")
        }
        if (age !is String) {
            print("age is not String")
        }


    }

    fun getStringLength(obj: Any): Int? {
        if (obj !is String) {
            return null
        } else if (obj.length > 0) {

            return obj.length
        } else {
            return 0
        }

    }


    fun foreach() {
        for (i in 1..100) print("$i,")
        for (x in 100 downTo 1 step 2) {
            print(",$x")
            if (x in 100 downTo 2) {
                print("{$x}")
            }
        }
        for (i in 1 until 10) {  // i in [1, 10) 排除了 10
            println(i)
        }

    }


}
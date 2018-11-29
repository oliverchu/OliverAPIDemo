package top.iofox.demo.app.oliverapidemo.kotlin

/**
 * Created by [Oliver Chu] on 2018/11/26 14:52
 */

class KotlinClass private constructor() {
    init {
        print("All is init.")
    }

    constructor(name: String, age: Int) : this() {
        this.age = age
        this.name = name;
    }


    lateinit var name: String;
    var age: Int = 0;
    var lastName: String = "Chu"
        get() = field.toUpperCase()
        set
    var no: Int = 100
        get() = field
        set(value) {
            if (value < 10) {
                field = value
            } else {
                field = -1
            }
        }

    var height: Float = 145.3f
        private set
    lateinit var subject: KotlinClassTest;


    override fun toString(): String {
        return "KotlinClass(name='$name', age=$age, height=$height)"
    }

}

class KotlinClassTest {
    fun main() {
        var person = KotlinClass("Oliver", 22)
        person.lastName = "Chu"
        person.no = 10
    }
}
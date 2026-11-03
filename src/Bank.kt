import java.util.Scanner

class BankAccount(val accountNumber: String, private var pinCode: String, initialBalance: Double) {
    private var balance: Double = initialBalance
    private val transactionHistory = ArrayList<String>()

    init {
        transactionHistory.add("الحساب أُنشئ برصيد ابتدائي: $initialBalance شيكل")
    }

    fun validatePin(enteredPin: String): Boolean {
        return this.pinCode == enteredPin
    }

    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
            transactionHistory.add("إيداع: +$amount شيكل | الرصيد الحالي: $balance")
            println("تمت عملية الإيداع بنجاح. رصيدك الحالي: $balance")
        } else {
            println("المبلغ المدخل غير صحيح!")
        }
    }

    fun withdraw(amount: Double) {
        if (amount <= 0) {
            println("المبلغ المدخل غير صحيح!")
        } else if (amount > balance) {
            println("عذراً، لا يوجد رصيد كافٍ!")
        } else {
            balance -= amount
            transactionHistory.add("سحب: -$amount شيكل | الرصيد الحالي: $balance")
            println("تم السحب بنجاح. المتبقي في الحساب: $balance")
        }
    }

    fun checkBalance() {
        println("رصيدك الحالي هو: $balance شيكل")
    }

    fun showHistory() {
        println("\n--- سجل العمليات الأخيرة ---")
        if (transactionHistory.isEmpty()) {
            println("لا توجد عمليات بعد.")
        } else {
            for (item in transactionHistory) {
                println(item)
            }
        }
        println("---------------------------")
    }
}

fun main() {
    val scanner = Scanner(System.`in`)

    println("=== أهلاً بك في نظام الحساب البنكي ===")
    print("أدخل رقم الحساب الجديد: ")
    val accNum = scanner.next()

    print("أدخل الرمز السري للحساب (PIN): ")
    val pin = scanner.next()

    print("أدخل مبلغ الإيداع الأولي: ")
    val initialBalance = scanner.nextDouble()

    val myAccount = BankAccount(accNum, pin, initialBalance)

    println("\nتم إنشاء الحساب بنجاح!")

    print("\nللدخول للحساب، أدخل الرمز السري: ")
    val enteredPin = scanner.next()

    if (!myAccount.validatePin(enteredPin)) {
        println("الرمز السري خطأ! تم إنهاء الجلسة.")
        return
    }

    var running = true

    while (running) {
        println("\n--- القائمة الرئيسية ---")
        println("1. إيداع مبلغ")
        println("2. سحب مبلغ")
        println("3. فحص الرصيد")
        println("4. عرض سجل العمليات")
        println("5. خروج")
        print("اختر رقم العملية: ")

        val choice = scanner.nextInt()

        when (choice) {
            1 -> {
                print("أدخل المبلغ المراد إيداعه: ")
                val amount = scanner.nextDouble()
                myAccount.deposit(amount)
            }
            2 -> {
                print("أدخل المبلغ المراد سحبه: ")
                val amount = scanner.nextDouble()
                myAccount.withdraw(amount)
            }
            3 -> myAccount.checkBalance()
            4 -> myAccount.showHistory()
            5 -> {
                println("شكراً لاستخدامك تطبيقنا البنكي. إلى اللقاء!")
                running = false
            }
            else -> println("خيار غير صحيح، حاول مرة أخرى.")
        }
    }
}
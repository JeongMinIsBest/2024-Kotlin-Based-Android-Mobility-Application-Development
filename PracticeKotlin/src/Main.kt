fun main(args: Array<String>) {

    var doctor: Person = Doctor()
    var professor: Person = Professor()

    doWork(doctor)
    doWork(professor)
}

fun doWork(person: Person) {

    person.whatsYourJob()
    when(person) {

        is Doctor -> person.fixit()

        is Professor -> person.teachit()

    }

}
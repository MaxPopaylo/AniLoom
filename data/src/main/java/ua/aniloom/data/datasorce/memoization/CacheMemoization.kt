package ua.aniloom.data.datasorce.memoization

object CacheMemoization {
    val todayScheduleLastVisiblePage = MemoizationValue<Int, Triple<Int, Int, String>>()
}

class MemoizationValue<Value, Input> {
    private val valueMap = mutableMapOf<Input, Value>()

    fun get(input: Input): Value? = valueMap[input]

    fun put(input: Input, value: Value) {
        valueMap[input] = value
    }

    fun getOrPut(input: Input,  defaultValue: () -> Value): Value {
        return valueMap.getOrPut(input, defaultValue)
    }
}
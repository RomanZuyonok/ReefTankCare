package com.reeftankcare.validation

import com.reeftankcare.R

private val regexTitle = Regex("""~!@#\$%^&*+-\s\d""")
private val regexInteger = Regex("""\p{Alpha}+|\p{Punct}+""")
private val regexDouble = Regex("""\p{Alpha}+|~!@#\$%^&*+-""")


fun inputValidationInteger(editText: String): ValidationResult {
    return when {
        editText.contains(regexInteger) -> ValidationResult.Invalid(R.string.err_input_integer)
        else -> ValidationResult.Valid
    }
}

fun inputValidationDouble(editText: String): ValidationResult {
    return when {
        editText.contains(regexDouble) -> ValidationResult.Invalid(R.string.err_input_integer)
        else -> ValidationResult.Valid
    }
}
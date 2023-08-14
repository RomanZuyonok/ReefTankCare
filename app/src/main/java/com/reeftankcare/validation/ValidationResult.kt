package com.reeftankcare.validation

sealed  class ValidationResult {

    object Valid : ValidationResult()
       class Invalid(val errorId: Int): ValidationResult()
}
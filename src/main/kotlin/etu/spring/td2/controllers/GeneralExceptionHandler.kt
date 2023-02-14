package etu.spring.td2.controllers

import etu.spring.td2.exceptions.ElementNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.NoHandlerFoundException

@ControllerAdvice
class GeneralExceptionHandler {
    @ExceptionHandler(value = [
        ElementNotFoundException::class,
        NoHandlerFoundException::class,
        MethodArgumentTypeMismatchException::class

    ])
    fun errorAction(ex:Exception):ModelAndView{
        val mv = ModelAndView("/include/error")
        mv.addObject("message",ex.message)
        return mv
    }

    @ExceptionHandler(value = [
        NoHandlerFoundException::class,
    ])
    fun notFoundAction(ex:NoHandlerFoundException):ModelAndView{
       return message("Page introuvable","La page ${ex.requestURL} n'existe pas")
    }

    private fun message(title:String, message:String):ModelAndView{
        val mv = ModelAndView("/include/error")
        mv.addObject("title", title)
        mv.addObject("message", message)
        return mv
    }
}
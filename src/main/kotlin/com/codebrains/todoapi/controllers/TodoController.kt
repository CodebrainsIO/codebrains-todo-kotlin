package com.codebrains.todoapi.controllers

import com.codebrains.todoapi.entities.Todo
import com.codebrains.todoapi.entities.TodoRepository
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/todos")
class TodoController(val todoRepository: TodoRepository) {
    @GetMapping
    fun getTodos() = todoRepository.findAll()

    @RequestMapping(path = [("/{todoId}")], method = [(RequestMethod.GET)])
    fun getTodo(@PathVariable("todoId") todoId: Long): Optional<Todo>? {
        return todoRepository.findById(todoId)
    }

    @PostMapping
    fun newTodo(@RequestBody todo: Todo): Todo {
        return todoRepository.save(todo)
    }

    @PutMapping(path = [("/{todoId}")])
    fun updateTodo(@PathVariable("todoId") todoId: Long, @RequestBody updatedTodo: Todo): Todo? {
        val oldTodo = todoRepository.findByIdOrNull(todoId)
        if(oldTodo == null){
            return oldTodo
        }
        return todoRepository.save(updatedTodo);


    }

    @RequestMapping(path = [("/{todoId}")], method = [(RequestMethod.DELETE)])
    fun deleteTodo(@PathVariable("todoId") todoId: Long) {
        todoRepository.deleteById(todoId)
    }
}
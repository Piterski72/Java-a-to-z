package ru.nivanov;

/**
 * MenuOutException user exception class.
 * @author nivanov.
 */
class UserNotFoundException extends RuntimeException {
    UserNotFoundException(String msg) {
        super(msg);
    }
}
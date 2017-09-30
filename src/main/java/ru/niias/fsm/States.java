package ru.niias.fsm;

public enum States {
    INIT, REQUEST,
    FORM_UPDATED,
    LOADED, LOAD_ERROR,
    CONFIRMED, CONFIRM_ERROR,
    FINISHED
}

package com.rekkursion.enigma.templates

abstract class InitializeTemplate {
    // to be implemented: initialize views
    protected abstract fun initViews()

    // to be implemented: initialize attributes of views
    protected abstract fun initAttributes()

    // to be implemented: initialize commands of views
    protected abstract fun initCommands()

    // to be implemented: initialize events of views
    protected abstract fun initEvents()

    // to be implemented: do after the initialization as the preprocessing
    protected abstract fun doAfterInitialization()

    // the template
    fun initialize() {
        initViews()
        initAttributes()
        initCommands()
        initEvents()
        doAfterInitialization()
    }
}
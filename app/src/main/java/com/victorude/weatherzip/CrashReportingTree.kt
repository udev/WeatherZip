package com.victorude.weatherzip

import timber.log.Timber

class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        Timber.d("This is where we should do production logging")
    }
}

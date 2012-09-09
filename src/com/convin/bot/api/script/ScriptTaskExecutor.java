package com.convin.bot.api.script;

import java.util.ArrayList;

/**
 * User: JuV
 * Date: 16.6.2012
 * Time: 19:20
 */
abstract class ScriptTaskExecutor implements Runnable {
    private final ArrayList<ScriptTask> scriptTasks = new ArrayList<ScriptTask>();
    private Thread thread;

    public void provide(final ScriptTask task) {
        scriptTasks.add(task);
    }

    ArrayList<ScriptTask> getScriptTasks() {
        return scriptTasks;
    }
}

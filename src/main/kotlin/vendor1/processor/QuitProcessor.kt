package vendor1.processor

import vendor1.Command

class QuitProcessor : Processor {
    override fun process(data: String): String? {
        val commands = data.split(" ")
        if(!validCommand(commands[0], Command.QUIT)) {
            return null
        }
        return "QUIT"
    }
}
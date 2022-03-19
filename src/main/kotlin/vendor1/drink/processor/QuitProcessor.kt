package vendor1.drink.processor

import vendor1.processor.Processor

class QuitProcessor : Processor {
    override fun process(command: String): String? {
        val commands = command.split(" ")
        val validCommand = Command.valueOf(commands[0])
        if (validCommand != Command.QUIT) {
            return null
        }
        return "QUIT"
    }
}
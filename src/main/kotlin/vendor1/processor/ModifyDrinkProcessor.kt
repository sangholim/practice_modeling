package vendor1.processor

import vendor1.dto.ModifyDrink

class ModifyDrinkProcessor : Processor {

    override fun process(command: String): String? {
        val commands = command.split(" ")
        val validCommand = Command.valueOf(commands[0])
        if(validCommand != Command.MODIFY) {
            return null
        }
        val payload = entityBinder<ModifyDrink>(commands[1])
        return vendorService.modifyDrink(payload)
    }
}
#Alex Valerozo


class Option:

    def __init__(self, text, action_command):
        self.text = text
        self.action_command = action_command

    def get_text(self):
        """
        Returns the text of the option.
        :return: text of the option
        """
        return self.text

    def set_text(self, text):
        """
        Sets the text for the option.
        :param text: The text to set for the option.
        """
        self.text = text

    def get_action_command(self):
        """
        Returns the action command of the option.
        :return: action command of the option
        """
        return self.action_command

    def set_action_command(self, action_command):
        """
        Sets the action command for the option.
        :param action_command: The action command to set.
        """
        self.action_command = action_command

    def __eq__(self, other):
        """
        Compares two Option objects for equality based on text and action command.
        :param other: The other object to compare.
        :return: True if both Option objects are equal, False otherwise.
        """
        if not isinstance(other, Option):
            return False
        return self.text == other.text and self.action_command == other.action_command

    def __hash__(self):
        """
        Returns a hash value for the Option object based on its text and action command.
        :return: hash value of the Option object
        """
        return hash((self.text, self.action_command))

    def __str__(self):
        """
        Returns a string representation of the Option object.
        :return: string representation of the Option object
        """
        return f"Option(text={self.text}, actionCommand={self.action_command})"
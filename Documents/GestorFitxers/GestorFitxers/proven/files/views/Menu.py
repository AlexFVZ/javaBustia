#Alex Valerozo

class Menu:
    def __init__(self, title=None):
       self.title = title
       self.options = []

    def get_title(self):
        """
        Returns the title of the menu.
        """
        return self.title

    def set_title(self, title):
        """
        Sets the title of the menu.
        """
        self.title = title

    def add_option(self, option):
        """
        Adds an option to the list of menu options.
        :param option: The option to be added.
        :return: True if the option was added, False otherwise.
        """
        self.options.append(option)

    def get_option(self, index):
        """
        Retrieves the option at a specific index.
        :param index: Index of the option.
        :return: The option at the specified index or None if the index is out of bounds.
        """
        if 0 <= index < len(self.options):
            return self.options[index]
        return None

    def remove_option(self, option):
        """
        Removes an option from the list.
        :param option: The option to be removed.
        :return: True if the option was removed, False otherwise.
        """
        if option in self.options:
            self.options.remove(option)
            return True
        return False

    def remove_option_by_index(self, index):
        """
        Removes an option from the list by its index.
        :param index: The index of the option to be removed.
        :return: The option removed, or None if the index is invalid.
        """
        if 0 <= index < len(self.options):
            return self.options.pop(index)
        return None

    def __str__(self):
        options_str = ', '.join([f"{option.get_text()}({option.get_action_command()})"
                                 for option in self.options])
        return f"Menu(title={self.title}, options=[{options_str}])"

    def show(self):
        """
        Displays the menu with the options.
        """
        print(f"============ {self.title} ============")
        for idx, option in enumerate(self.options):
            print(f"[{idx}] {option.get_text()}")

    def get_selected_option(self):
        """
        Gets the option selected by the user.
        :return: The index of the selected option or -1 if invalid input.
        """
        try:
            opt = int(input("Select an option: "))
            if 0 <= opt < len(self.options):
                return opt
            return -1
        except ValueError:
            return -1

    def get_selected_option_action_command(self):
        """
        Returns the action command associated with the selected option.
        """
        option_number = self.get_selected_option()
        if 0 <= option_number < len(self.options):
            return self.options[option_number].get_action_command()
        return None
#Alex Valerozo

from proven.files.views.Menu import Menu
from proven.files.views.Option import Option
class FilesMenu(Menu):
    """
    Displays the menu options for managing files.
    """
    def __init__(self):
        super().__init__("Files Manager main menu")

        # add options to menu
        self.add_option(Option("create a file", "create_file"))
        self.add_option(Option("Read the content of a file", "read_file"))
        self.add_option(Option("Write in a file", "write_to_file"))
        self.add_option(Option("Remove file", "delete_file"))
        self.add_option(Option("Exit", "exit"))
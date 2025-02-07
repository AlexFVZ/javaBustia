#Alex Valerozo

from proven.files.views.FilesMenu import FilesMenu

class FileView:

    def __init__(self, control, model):
        """
        Initializes the view with a controller and a model.
        :param control: FileController, controller instance
        :param model: FileModel, model instance
        """
        self.control = control
        self.menu = FilesMenu()  # This creates the menu in the view

    def get_content(self):
        """Prompts the user for the content to write in the file and returns it."""
        return self.show_input_dialog("Enter the content to write into the file: ")

    def show_input_dialog(self, message):
        """
        Requests input from the user with a custom message.
        :param message: str, message to display
        :return: str, user input
        """
        return input(message)

    def get_filename(self):
        """Prompts the user for the filename and returns it."""
        return self.show_input_dialog("Enter the filename: ")

    def show_message(self, message):
        """Displays a message to the user."""
        print(message)

    def display(self):
        """
        Shows the main menu and processes user actions.
        """
        while True:
            self.menu.show()
            action = self.menu.get_selected_option_action_command()
            self.process_action(action)

    def process_action(self, action):
        """
        Handles the actions sent from the menu.
        :param action: str, action command
        """
        if action:
            self.control.process_request(action)


    def show_file_table(self, data):
        """
        Displays a list of files in a table-like format.
        :param data: list of File objects, the files to display
        """
        if data:
            for file in data:
                print(file)  # Make sure your File class has a useful __str__ method
            print(f"{len(data)} elements found.")
        else:
            print("No files found.")
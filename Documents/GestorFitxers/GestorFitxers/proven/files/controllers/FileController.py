#Alex Valerozo

from proven.files.model.File import File
from proven.files.views.FilesView import FileView

class FileController:
    def __init__(self, model):
        self.model = model
        self.view = FileView(self, model)
        self.view.display()

    def process_request(self, action):
        """
        Processes the user action and delegates to the corresponding method.
        If the action is invalid, it shows an error message.
        """
        action = action if action else "wrong_action"
        if action == "create_file":
            self.create_file()
        elif action == "read_file":
            self.read_file()
        elif action == "write_to_file":
            self.write_to_file()
        elif action == "delete_file":
            self.delete_file()
        elif action == "exit":
            self.exit_application()
        else:
            self.view.show_message("Wrong option. Please select a valid action.")

    def create_file(self):
        """Handles file creation by asking for the filename and passing it to the model."""
        filename = self.view.get_filename()  # Get filename from the view
        result = self.model.create_file(filename)  # Call model to create file
        self.view.show_message(result) # Show result to the user

    def write_to_file(self):
        """Handles writing to a file by asking for the filename and content, then passing it to the model."""
        filename = self.view.get_filename()  # Get filename from the view
        content = self.view.get_content()  # Get content to write
        result = self.model.write_to_file(filename, content)  # Call model to write content
        self.view.show_message(result)  # Show result to the user

    def read_file(self):
        """Handles reading a file by asking for the filename and passing it to the model."""
        filename = self.view.get_filename()  # Get filename from the view
        content = self.model.read_file(filename)  # Call model to read file content
        self.view.show_message(content)  # Show content to the user

    def delete_file(self):
        """Handles file deletion by asking for the filename and passing it to the model."""
        filename = self.view.get_filename()  # Get filename from the view
        result = self.model.delete_file(filename)  # Call model to delete the file
        self.view.show_message(result)  # Show result to the user

    def exit_application(self):
        """Exit the application."""
        import sys
        sys.exit(0)
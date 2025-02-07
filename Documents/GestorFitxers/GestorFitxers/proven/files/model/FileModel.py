
from proven.files.model.File import File
from proven.files.views.FilesView import FileView
import os

class FileModel:

    def __init__(self):
        pass

    def find(self, entity):
        """
        Finds a file in the filesystem by its name.
        :param entity: File object to find by name
        :return: File object if found, None if not found
        """
        # This can be improved if you're storing a list of files or searching a directory, but it's fine as it is.
        file_path = entity.get_name()
        if os.path.exists(file_path):
            return File(name=entity.get_name(), content=self.read_file(file_path))
        return None


    def create_file(self, filename):
        """Creates a new file if it does not already exist. Returns a success or error message."""
        if os.path.exists(filename):
            return f"The file '{filename}' already exists."
        try:
            with open(filename, 'w') as file:  # Create the file (empty)
                pass
            return f"The file '{filename}' has been created successfully."
        except Exception as e:
            return f"Error creating the file: {e}"

    def write_to_file(self, filename, content):
        """Writes content to a file. If the file doesn't exist, it returns an error message."""
        try:
            with open(filename, 'a') as file:  # 'a' appends to the file
                file.write(content + '\n')  # Write content with a newline
            return f"Content has been written to '{filename}' successfully."
        except FileNotFoundError:
            return f"The file '{filename}' does not exist."
        except Exception as e:
            return f"Error writing to the file: {e}"

    def read_file(self, filename):
        """Reads the content of a file and returns it. If the file doesn't exist, an error message is returned."""
        try:
            with open(filename, 'r') as file:
                content = file.read()  # Read entire file content
            return content
        except FileNotFoundError:
            return f"The file '{filename}' does not exist."
        except Exception as e:
            return f"Error reading the file: {e}"

    def delete_file(self, filename):
        """Deletes the specified file. If the file doesn't exist, returns an error message."""
        try:
            os.remove(filename)  # Delete the file
            return f"The file '{filename}' has been deleted successfully."
        except FileNotFoundError:
            return f"The file '{filename}' does not exist."
        except Exception as e:
            return f"Error deleting the file: {e}"


    def __str__(self):
        """
        String FileModel
        :return: str
        """
        return f"FileModel{{File={self.files}}}"
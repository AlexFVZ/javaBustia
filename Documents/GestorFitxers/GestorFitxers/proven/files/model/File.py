#Alex Valerozo

class File:
    def __init__(self, name=None, content=None):
        self.name = name  # The name of the file
        self.content = content  # The content of the file

    def get_name(self):
        """Returns the name of the file."""
        return self.name

    def set_name(self, name):
        """Sets the name of the file."""
        self.name = name

    def get_content(self):
        """Returns the content of the file."""
        return self.content

    def set_content(self, content):
        """Sets the content of the file."""
        self.content = content

    def __hash__(self):
        """Returns a hash based on the file's name."""
        return hash(self.name)

    def __eq__(self, other):
        """Checks equality between two File objects based on their names."""
        if other is None:
            return False
        if isinstance(other, File):
            return self.name == other.name
        return False

    def __str__(self):
        """Returns a string representation of the file."""
        return f"File{{name={self.name}; content={self.content}}}"

#Alex Valerozo

from proven.files.model.FileModel import FileModel
from proven.files.controllers.FileController import FileController

class FilesApp:
    def main(self):
        # Create the model and controller
        model = FileModel()
        control = FileController(model)


if __name__ == "__main__":
    # Start the application
    app = FilesApp()
    app.main()
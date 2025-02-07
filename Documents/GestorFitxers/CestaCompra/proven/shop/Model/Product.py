#Alex Valerozo

class Product:
    def __init__(self, name, price):
        self.name = name
        self.price = price

    def get_name(self):
        """Returns the name of the product."""
        return self.name

    def set_name(self, name):
        """Sets the name of the product."""
        self.name = name

    def get_price(self):
        """Returns the content of the product."""
        return self.price

    def set_price(self, price):
        """Sets the content of the product."""
        self.price = price

    def __hash__(self):
        """Returns a hash based on the product's name."""
        return hash(self.name)

    def __eq__(self, other):
        """Checks equality between two product objects based on their names."""
        if other is None:
            return False
        if isinstance(other, Product):
            return self.name == other.name
        return False

    def __str__(self):
        """Returns a string representation of the product."""
        return f"Product{{name={self.name}; content={self.price:.2f}}}"
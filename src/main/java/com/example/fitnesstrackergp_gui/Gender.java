package com.example.fitnesstrackergp_gui;

public enum Gender {
    MALE, FEMALE, OTHER;

    /**
     * Converts a character representation of gender into a corresponding Gender enum value.
     * The method interprets the character by converting it to uppercase.
     *
     * @param c The character representing the gender.
     * @return The corresponding Gender enum value: `MALE`, `FEMALE`, or `OTHER`.
     */
    public static Gender fromChar(char c) {
        char genderInitial = Character.toUpperCase(c);
        switch (genderInitial) {
            case 'M':
                return MALE;
            case 'F':
                return FEMALE;
            default:
                return OTHER;
        }
    }

    /**
     * Converts the Gender enum value to its corresponding character representation.
     *
     * @return A character representing the gender.
     */
    public char toChar() {
        switch (this) {
            case MALE:
                return 'M';
            case FEMALE:
                return 'F';
            default:
                return 'O';
        }
    }
}

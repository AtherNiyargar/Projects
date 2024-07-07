#include <stdio.h>
#include <string.h>
#include <ctype.h>

int checker(char *password)
{
    // I'm not using booleans because they are just integers of 0 and 1 (atleast in c)
    // but they increase the readability
    // I like to include less header files

    int upper_case = 0,
        lower_case = 0,
        special_case = 0,
        alpha_numeric = 0;

    for (int i = 0; i < strlen(password); i++)
    {
        if (!alpha_numeric)
            if (isalnum(password[i]))
                alpha_numeric = 1;

        if (!upper_case)
            if (isupper(password[i]))
                upper_case = 1;

        if (!lower_case)
            if (islower(password[i]))
                lower_case = 1;

        // puctuation characters are,
        // ! " # $ % & ' ( ) * + , - . / : ; < = > ? @ [ \ ] ^ _ ` { | } ~
        if (!special_case)
            if (ispunct(password[i]))
                special_case = 1;

        if (alpha_numeric && special_case && lower_case && upper_case)
            return 1;
    }
    return 0;
}

int main()
{
    char password[50];

    while (1)
    {

        // These are the color codes for texts in linux
        /*
        Black:  \e[30m
        Red:    \e[31m
        Green:  \e[32m
        Yellow: \e[33m
        Blue:   \e[34m
        Magenta: \e[35m
        Cyan:   \e[36m
        White:  \e[37m
        Reset:  \e[0m (resets text color to default)
        */

        printf("\e[0mEnter your password : ");
        fgets(password, 50, stdin); // Actually, it will take 49 characters

        int result = 0;
        result = checker(password);
        if (result)
        {
            printf("\e[32mThe password is strong\n");
            break;
        }
        else
            printf("\e[31mThe password is weak\n");
    }
    return 0;
}

#include "mapwindow.hpp"

#include <QApplication>

int main(int argc, char **argv)
{
    // Uncomment for software rendering:
    //QApplication::setAttribute(Qt::AA_UseSoftwareOpenGL);

    QApplication app(argc, argv);

    QMapboxGLSettings settings;
    settings.setAccessToken("pk.eyJ1IjoidG1wc2FudG9zIiwiYSI6ImNqMWVzZWthbDAwMGIyd3M3ZDR0aXl3cnkifQ.FNxMeWCZgmujeiHjl44G9Q");

    MapWindow window(settings);

    window.resize(800, 600);
    window.show();

    if (argc == 2 && QString("--test") == argv[1]) {
        window.selfTest();
    }

    return app.exec();
}

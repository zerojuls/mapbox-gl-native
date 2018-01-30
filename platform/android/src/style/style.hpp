#pragma once

#include <mbgl/style/style.hpp>

#include <jni/jni.hpp>

namespace mbgl {
namespace android {

/**
 * Peer class for the Android Style holder.
 */
class Style {
public:

    // TODO move to style package
    static constexpr auto Name() { return "com/mapbox/mapboxsdk/maps/Style"; };

    Style(jni::JNIEnv&, mbgl::style::Style&);

    ~Style();

    static jni::Class<Style> javaClass;

    // TODO add methods
    static void registerNative(jni::JNIEnv&);

    jni::Object<Style> createJavaPeer(jni::JNIEnv&);
    jni::String getJSON(jni::JNIEnv&);

private:
    // Raw pointer that is valid at all times.
    mbgl::style::Style& style;

    // Set when the source is added to a map.
    jni::UniqueObject<Style> javaPeer;
};

}
}
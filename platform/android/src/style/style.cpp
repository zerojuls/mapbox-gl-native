#include "style.hpp"

namespace mbgl {
namespace android {

    Style::Style(jni::JNIEnv& env, mbgl::style::Style& coreStyle)
        : style(coreStyle)
        , javaPeer(createJavaPeer(env).NewGlobalRef(env)){

    }

    Style::~Style() {
    }

    jni::Object<Style> Style::createJavaPeer(jni::JNIEnv& env) {
        static auto constructor = Style::javaClass.template GetConstructor<jni::jlong>(env);
        return jni::Object<Style>(Style::javaClass.New(env, constructor, reinterpret_cast<jni::jlong>(this)).Get());
    }

    jni::Class<Style> Style::javaClass;

    jni::String Style::getJSON(jni::JNIEnv& env) {
        return jni::Make<jni::String>(env, style.getJSON());
    }


void Style::registerNative(jni::JNIEnv& env) {
    //Register classes
    Style::javaClass = *jni::Class<Style>::Find(env).NewGlobalRef(env).release();

    #define METHOD(MethodPtr, name) jni::MakeNativePeerMethod<decltype(MethodPtr), (MethodPtr)>(name)

    // Register the peer
    jni::RegisterNativePeer<Style>(
        env, Style::javaClass, "nativePtr",
        std::make_unique<Style, JNIEnv&, mbgl::style::Style&>,
        "initialize",
        "finalize"
);
}


} // namespace mbgl
} // namespace android
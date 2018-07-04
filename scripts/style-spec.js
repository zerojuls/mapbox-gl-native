var spec = module.exports = require('../mapbox-gl-js/src/style-spec/reference/v8');

// Make temporary modifications here when Native doesn't have all features that JS has.
delete spec.paint_line['line-gradient'];

// https://github.com/mapbox/mapbox-gl-native/issues/12300
delete spec.layout_symbol['symbol-placement']['values']['line-center'];
spec.layout_symbol['icon-keep-upright']['requires'][2]['symbol-placement'] = 'line';
spec.layout_symbol['text-keep-upright']['requires'][2]['symbol-placement'] = 'line';
spec.layout_symbol['text-max-angle']['requires'][1]['symbol-placement'] = 'line';
spec.layout_symbol['icon-rotation-alignment']['values']['map']['doc'] = spec.layout_symbol['icon-rotation-alignment']['values']['map']['doc'].replace(" or `line-center`","");
spec.layout_symbol['icon-rotation-alignment']['values']['auto']['doc'] = spec.layout_symbol['icon-rotation-alignment']['values']['auto']['doc'].replace(" or `line-center`","");
spec.layout_symbol['text-rotation-alignment']['values']['map']['doc'] = spec.layout_symbol['text-rotation-alignment']['values']['map']['doc'].replace(" or `line-center`","");
spec.layout_symbol['text-rotation-alignment']['values']['auto']['doc'] = spec.layout_symbol['text-rotation-alignment']['values']['auto']['doc'].replace(" or `line-center`","");

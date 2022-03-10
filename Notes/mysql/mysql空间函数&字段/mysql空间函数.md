|      | ***\*名称\****                                               | ***\*描述\****                       |
| ---- | ------------------------------------------------------------ | ------------------------------------ |
| 1    | [ST_StartPoint()](https://dev.mysql.com/doc/refman/8.0/en/gis-linestring-property-functions.html#function_st-startpoint) | LineString的起始点                   |
| 2    | [ST_EndPoint()](https://dev.mysql.com/doc/refman/8.0/en/gis-linestring-property-functions.html#function_st-endpoint) | LineString的终点                     |
| 3    | [ST_Transform()](https://dev.mysql.com/doc/refman/8.0/en/spatial-operator-functions.html#function_st-transform) | 变换几何的坐标                       |
| 4    | [ST_GeoHash()](https://dev.mysql.com/doc/refman/8.0/en/spatial-geohash-functions.html#function_st-geohash) | 产生geohash值                        |
| 5    | [ST_LongFromGeoHash()](https://dev.mysql.com/doc/refman/8.0/en/spatial-geohash-functions.html#function_st-longfromgeohash) | 从geohash值返回经度                  |
| 6    | [ST_LatFromGeoHash()](https://dev.mysql.com/doc/refman/8.0/en/spatial-geohash-functions.html#function_st-latfromgeohash) | 从geohash值返回纬度                  |
| 7    | [ST_GeomFromGeoJSON()](https://dev.mysql.com/doc/refman/8.0/en/spatial-geojson-functions.html#function_st-geomfromgeojson) | 从GeoJSON对象生成几何                |
| 8    | [Polygon()](https://dev.mysql.com/doc/refman/8.0/en/gis-mysql-specific-functions.html#function_polygon) | 从LineString参数构造多边形           |
| 9    | [ST_PointN()](https://dev.mysql.com/doc/refman/8.0/en/gis-linestring-property-functions.html#function_st-pointn) | 从LineString返回第N个点              |
| 10   | [MultiLineString()](https://dev.mysql.com/doc/refman/8.0/en/gis-mysql-specific-functions.html#function_multilinestring) | 从LineString值构造MultiLineString    |
| 11   | [LineString()](https://dev.mysql.com/doc/refman/8.0/en/gis-mysql-specific-functions.html#function_linestring) | 从Point值构造LineString              |
| 12   | [MultiPoint()](https://dev.mysql.com/doc/refman/8.0/en/gis-mysql-specific-functions.html#function_multipoint) | 从Point值构造MultiPoint              |
| 13   | [MultiPolygon()](https://dev.mysql.com/doc/refman/8.0/en/gis-mysql-specific-functions.html#function_multipolygon) | 从Polygon值构造MultiPolygon          |
| 14   | [ST_GeomFromWKB()， ST_GeometryFromWKB()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkb-functions.html#function_st-geomfromwkb) | 从WKB返回几何                        |
| 15   | [ST_GeomCollFromWKB()， ST_GeometryCollectionFromWKB()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkb-functions.html#function_st-geomcollfromwkb) | 从WKB返回几何集合                    |
| 16   | [ST_LineFromWKB()， ST_LineStringFromWKB()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkb-functions.html#function_st-linefromwkb) | 从WKB构造LineString                  |
| 17   | [ST_MLineFromWKB()， ST_MultiLineStringFromWKB()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkb-functions.html#function_st-mlinefromwkb) | 从WKB构造MultiLineString             |
| 18   | [ST_MPointFromWKB()， ST_MultiPointFromWKB()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkb-functions.html#function_st-mpointfromwkb) | 从WKB构造MultiPoint                  |
| 19   | [ST_MPolyFromWKB()， ST_MultiPolygonFromWKB()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkb-functions.html#function_st-mpolyfromwkb) | 从WKB构造MultiPolygon                |
| 20   | [ST_PointFromWKB()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkb-functions.html#function_st-pointfromwkb) | 从WKB构造点                          |
| 21   | [ST_PolyFromWKB()， ST_PolygonFromWKB()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkb-functions.html#function_st-polyfromwkb) | 从WKB构造多边形                      |
| 22   | [ST_GeomFromText()， ST_GeometryFromText()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkt-functions.html#function_st-geomfromtext) | 从WKT返回几何                        |
| 23   | [ST_GeomCollFromText()，ST_GeometryCollectionFromText()，ST_GeomCollFromTxt()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkt-functions.html#function_st-geomcollfromtext) | 从WKT返回几何集合                    |
| 24   | [ST_PointFromText()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkt-functions.html#function_st-pointfromtext) | 从WKT构建点                          |
| 25   | [ST_LineFromText()， ST_LineStringFromText()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkt-functions.html#function_st-linefromtext) | 从WKT构造LineString                  |
| 26   | [ST_MLineFromText()， ST_MultiLineStringFromText()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkt-functions.html#function_st-mlinefromtext) | 从WKT构造MultiLineString             |
| 27   | [ST_MPointFromText()， ST_MultiPointFromText()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkt-functions.html#function_st-mpointfromtext) | 从WKT构造MultiPoint                  |
| 28   | [ST_MPolyFromText()， ST_MultiPolygonFromText()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkt-functions.html#function_st-mpolyfromtext) | 从WKT构造MultiPolygon                |
| 29   | [ST_PolyFromText()， ST_PolygonFromText()](https://dev.mysql.com/doc/refman/8.0/en/gis-wkt-functions.html#function_st-polyfromtext) | 从WKT构造多边形                      |
| 30   | [GeomCollection()](https://dev.mysql.com/doc/refman/8.0/en/gis-mysql-specific-functions.html#function_geomcollection) | 从几何构造几何集合                   |
| 31   | [GeometryCollection()](https://dev.mysql.com/doc/refman/8.0/en/gis-mysql-specific-functions.html#function_geometrycollection) | 从几何构造几何集合                   |
| 32   | [ST_GeometryN()](https://dev.mysql.com/doc/refman/8.0/en/gis-geometrycollection-property-functions.html#function_st-geometryn) | 从几何集合中返回第N个几何            |
| 33   | [ST_AsGeoJSON()](https://dev.mysql.com/doc/refman/8.0/en/spatial-geojson-functions.html#function_st-asgeojson) | 从几何体生成GeoJSON对象              |
| 34   | [ST_AsBinary()， ST_AsWKB()](https://dev.mysql.com/doc/refman/8.0/en/gis-format-conversion-functions.html#function_st-asbinary) | 从内部几何格式转换为WKB              |
| 35   | [ST_AsText()， ST_AsWKT()](https://dev.mysql.com/doc/refman/8.0/en/gis-format-conversion-functions.html#function_st-astext) | 从内部几何格式转换为WKT              |
| 36   | [Point()](https://dev.mysql.com/doc/refman/8.0/en/gis-mysql-specific-functions.html#function_point) | 从坐标构造点                         |
| 37   | [ST_Length()](https://dev.mysql.com/doc/refman/8.0/en/gis-linestring-property-functions.html#function_st-length) | 返回LineString的长度                 |
| 38   | [ST_NumPoints()](https://dev.mysql.com/doc/refman/8.0/en/gis-linestring-property-functions.html#function_st-numpoints) | 返回LineString中的点数               |
| 39   | [ST_X()](https://dev.mysql.com/doc/refman/8.0/en/gis-point-property-functions.html#function_st-x) | 返回Point的X坐标                     |
| 40   | [ST_Y()](https://dev.mysql.com/doc/refman/8.0/en/gis-point-property-functions.html#function_st-y) | 返回Point的Y坐标                     |
| 41   | [ST_Longitude()](https://dev.mysql.com/doc/refman/8.0/en/gis-point-property-functions.html#function_st-longitude) | 返回Point的经度                      |
| 42   | [ST_Latitude()](https://dev.mysql.com/doc/refman/8.0/en/gis-point-property-functions.html#function_st-latitude) | 返回Point的纬度                      |
| 43   | [ST_InteriorRingN()](https://dev.mysql.com/doc/refman/8.0/en/gis-polygon-property-functions.html#function_st-interiorringn) | 返回Polygon的第N个内环               |
| 44   | [ST_ExteriorRing()](https://dev.mysql.com/doc/refman/8.0/en/gis-polygon-property-functions.html#function_st-exteriorring) | 返回Polygon的外环                    |
| 45   | [ST_Area()](https://dev.mysql.com/doc/refman/8.0/en/gis-polygon-property-functions.html#function_st-area) | 返回Polygon或MultiPolygon区域        |
| 46   | [ST_Union()](https://dev.mysql.com/doc/refman/8.0/en/spatial-operator-functions.html#function_st-union) | 返回点集两个几何的并集               |
| 47   | [ST_SymDifference()](https://dev.mysql.com/doc/refman/8.0/en/spatial-operator-functions.html#function_st-symdifference) | 返回点设置两个几何的对称差异         |
| 48   | [ST_Intersection()](https://dev.mysql.com/doc/refman/8.0/en/spatial-operator-functions.html#function_st-intersection) | 返回点设置两个几何的交集             |
| 49   | [ST_NumInteriorRing()， ST_NumInteriorRings()](https://dev.mysql.com/doc/refman/8.0/en/gis-polygon-property-functions.html#function_st-numinteriorrings) | 返回多边形内圈的数量                 |
| 50   | [ST_Envelope()](https://dev.mysql.com/doc/refman/8.0/en/gis-general-property-functions.html#function_st-envelope) | 返回几何的MBR                        |
| 51   | [ST_SRID()](https://dev.mysql.com/doc/refman/8.0/en/gis-general-property-functions.html#function_st-srid) | 返回几何的空间参考系统ID             |
| 52   | [ST_NumGeometries()](https://dev.mysql.com/doc/refman/8.0/en/gis-geometrycollection-property-functions.html#function_st-numgeometries) | 返回几何集合中的几何数量             |
| 53   | [ST_GeometryType()](https://dev.mysql.com/doc/refman/8.0/en/gis-general-property-functions.html#function_st-geometrytype) | 返回几何类型的名称                   |
| 54   | [ST_ConvexHull()](https://dev.mysql.com/doc/refman/8.0/en/spatial-operator-functions.html#function_st-convexhull) | 返回几何体的凸包                     |
| 55   | [ST_Simplify()](https://dev.mysql.com/doc/refman/8.0/en/spatial-convenience-functions.html#function_st-simplify) | 返回简化几何                         |
| 56   | [ST_Buffer()](https://dev.mysql.com/doc/refman/8.0/en/spatial-operator-functions.html#function_st-buffer) | 返回距离几何体的给定距离内的点的几何 |
| 57   | [ST_Validate()](https://dev.mysql.com/doc/refman/8.0/en/spatial-convenience-functions.html#function_st-validate) | 返回验证的几何体                     |
| 58   | [ST_Centroid()](https://dev.mysql.com/doc/refman/8.0/en/gis-polygon-property-functions.html#function_st-centroid) | 返回质心作为一个点                   |
| 59   | [ST_Dimension()](https://dev.mysql.com/doc/refman/8.0/en/gis-general-property-functions.html#function_st-dimension) | 几何尺寸                             |
| 60   | [ST_IsClosed()](https://dev.mysql.com/doc/refman/8.0/en/gis-linestring-property-functions.html#function_st-isclosed) | 几何是否封闭且简单                   |
| 61   | [ST_IsSimple()](https://dev.mysql.com/doc/refman/8.0/en/gis-general-property-functions.html#function_st-issimple) | 几何是否简单                         |
| 62   | [ST_IsValid()](https://dev.mysql.com/doc/refman/8.0/en/spatial-convenience-functions.html#function_st-isvalid) | 几何是否有效                         |
| 63   | [ST_PointFromGeoHash()](https://dev.mysql.com/doc/refman/8.0/en/spatial-geohash-functions.html#function_st-pointfromgeohash) | 将geohash值转换为POINT值             |
| 64   | [ST_SwapXY()](https://dev.mysql.com/doc/refman/8.0/en/gis-format-conversion-functions.html#function_st-swapxy) | 交换X / Y坐标的返回参数              |
| 65   | [ST_MakeEnvelope()](https://dev.mysql.com/doc/refman/8.0/en/spatial-convenience-functions.html#function_st-makeenvelope) | 两点左右的矩形                       |
| 66   | [MBREquals()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-mbr.html#function_mbrequals) | 两个几何的MBR是否相等                |
| 67   | [MBRIntersects()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-mbr.html#function_mbrintersects) | 两个几何的MBR是否相交                |
| 68   | [MBROverlaps()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-mbr.html#function_mbroverlaps) | 两个几何的MBR是否重叠                |
| 69   | [ST_Difference()](https://dev.mysql.com/doc/refman/8.0/en/spatial-operator-functions.html#function_st-difference) | 两个几何的返回点集差异               |
| 70   | [MBRDisjoint()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-mbr.html#function_mbrdisjoint) | 两个几何形状的MBR是否不相交          |
| 71   | [ST_Distance_Sphere()](https://dev.mysql.com/doc/refman/8.0/en/spatial-convenience-functions.html#function_st-distance-sphere) | 两个几何形状之间的最小地球距离       |
| 72   | [MBRTouches()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-mbr.html#function_mbrtouches) | 两种几何形状的MBR是否接触            |
| 73   | [ST_Buffer_Strategy()](https://dev.mysql.com/doc/refman/8.0/en/spatial-operator-functions.html#function_st-buffer-strategy) | 为ST_Buffer（）生成策略选项          |
| 74   | [MBRCoveredBy()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-mbr.html#function_mbrcoveredby) | 一个MBR是否被另一个MBR覆盖           |
| 75   | [MBRCovers()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-mbr.html#function_mbrcovers) | 一个MBR是否涵盖另一个MBR             |
| 76   | [MBRContains()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-mbr.html#function_mbrcontains) | 一个几何的MBR是否包含另一个几何的MBR |
| 77   | [MBRWithin()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-mbr.html#function_mbrwithin) | 一个几何的MBR是否在另一个几何的MBR内 |
| 78   | [ST_Contains()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-object-shapes.html#function_st-contains) | 一个几何是否包含另一个               |
| 79   | [ST_Touches()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-object-shapes.html#function_st-touches) | 一个几何是否接触另一个               |
| 80   | [ST_Disjoint()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-object-shapes.html#function_st-disjoint) | 一个几何是否与另一个几何脱节         |
| 81   | [ST_Equals()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-object-shapes.html#function_st-equals) | 一个几何是否与另一个几何相等         |
| 82   | [ST_Crosses()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-object-shapes.html#function_st-crosses) | 一个几何是否与另一个几何相交         |
| 83   | [ST_Intersects()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-object-shapes.html#function_st-intersects) | 一个几何是否与另一个相交             |
| 84   | [ST_Overlaps()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-object-shapes.html#function_st-overlaps) | 一个几何是否与另一个重叠             |
| 85   | [ST_Within()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-object-shapes.html#function_st-within) | 一个几何是否在另一个之内             |
| 86   | [ST_Distance()](https://dev.mysql.com/doc/refman/8.0/en/spatial-relation-functions-object-shapes.html#function_st-distance) | 一个几何与另一个几何的距离           |
| 87   | [ST_IsEmpty()](https://dev.mysql.com/doc/refman/8.0/en/gis-general-property-functions.html#function_st-isempty) | 占位符功能                           |
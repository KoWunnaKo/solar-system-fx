package org.mahefa.common.utils;

import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import org.mahefa.common.constants.CelestialBodyCategory;
import org.mahefa.common.utils.math.astronomy.AstroMath;
import org.mahefa.data.CelestialBody;
import org.mahefa.data.PhysicalCharacteristic;
import org.mahefa.data.meeus.jean.Coordinate;

public class NodeUtils {

    public static Node buildSphere(CelestialBody celestialBody, Coordinate coordinate, double scaleDistanceValue) {
        final PhysicalCharacteristic physicalCharacteristic = celestialBody.getPhysicalCharacteristic();
        final String designation = celestialBody.getDesignation().toLowerCase();
        final double axialTilt = physicalCharacteristic.getAxialTilt();
        final double radius = (celestialBody.getCelestialBodyCategory().equals(CelestialBodyCategory.STAR)) ? 30d : 9d;
        final PhongMaterial phongMaterial = (celestialBody.getCelestialBodyCategory().equals(CelestialBodyCategory.STAR))
                ? TextureUtils.getTexture(designation) : TextureUtils.getTextureFromColor(designation);

        Sphere sphere = new Sphere(radius);
        sphere.setId(designation);
        sphere.setTranslateX((coordinate.getX() * AstroMath.AU * 1e-3) * scaleDistanceValue);
        sphere.setTranslateY((coordinate.getZ() * AstroMath.AU * 1e-3) * scaleDistanceValue);
        sphere.setTranslateZ((coordinate.getY() * AstroMath.AU * 1e-3) * scaleDistanceValue);
        sphere.setMaterial(phongMaterial);
        sphere.setUserData(celestialBody);
        sphere.setDrawMode(DrawMode.FILL);
        sphere.setCullFace(CullFace.BACK);
        sphere.getTransforms().add(new Rotate(axialTilt));
        sphere.setRotationAxis(Rotate.Y_AXIS);
        sphere.setCache(true);
        sphere.setCacheHint(CacheHint.SPEED);

        return sphere;
    }
}

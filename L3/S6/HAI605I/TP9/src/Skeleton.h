#ifndef SKELETON_H
#define SKELETON_H


#include <vector>
#include <queue>
#include <map>
#include <cassert>
#include <string>
#include <iostream>
#include <fstream>
#include "Vec3.h"

#include <cmath>

#include <GL/glut.h>


// -------------------------------------------
// Basic Skeleton class
// -------------------------------------------

struct Articulation {
    // membres :
    Vec3 position; // une position

    int father_bone; // there should be only 1
    std::vector< unsigned int > child_bones;

    void setFatherBone( int f ) {
        if( father_bone >= 0 ) {
            assert(father_bone == f);
        }
        father_bone = f;
    }
    bool isRoot() const {
        return father_bone == -1;
    }

    Articulation() : father_bone(-1) { child_bones.clear(); }
};

struct Bone {
    // membres :
    unsigned int joints[2];

    int father_bone; // there should be only 1
    std::vector< unsigned int > child_bones;

    void setFatherBone( int f ) {
        if( father_bone >= 0 ) {
            assert(father_bone == f);
        }
        father_bone = f;
    }
    bool isRoot() const {
        return father_bone == -1;
    }

    Bone() : father_bone(-1) { child_bones.clear(); }
};

struct BoneTransformation{
    // membres :
    Mat3 local_rotation;

    Mat3 world_space_rotation;
    Vec3 world_space_translation;

    BoneTransformation() : local_rotation(Mat3::Identity()) ,  world_space_rotation(Mat3::Identity()) , world_space_translation(0,0,0) {}
};

struct SkeletonTransformation{
    // membres :
    std::vector< BoneTransformation > bone_transformations;
    std::vector< Vec3 > articulations_transformed_position;
};


struct Skeleton {
    // membres :
    std::vector< Articulation > articulations;
    std::vector< Bone > bones;
    std::vector< unsigned int > ordered_bone_indices; // process them by order in the hierarchy

    void buildStructure() {
        ordered_bone_indices.clear();
        std::vector< unsigned int > rootBones; // why not have several

        for( unsigned int b = 0 ; b < bones.size() ; ++b ) {
            Articulation & a0 = articulations[ bones[b].joints[0] ];
            Articulation & a1 = articulations[ bones[b].joints[1] ];
            a0.child_bones.push_back( b );
            a1.setFatherBone( b );
        }

        for( unsigned int aIdx = 0 ; aIdx < articulations.size() ; ++aIdx ) {
            Articulation & a = articulations[ aIdx ];
            if( a.isRoot() ) {
                for( unsigned int bIt = 0 ; bIt < a.child_bones.size() ; ++bIt ) {
                    unsigned int b = a.child_bones[bIt];
                    rootBones.push_back( b );
                }
            }
            else {
                unsigned int bfIdx = a.father_bone;
                Bone & bf = bones[bfIdx];
                for( unsigned int bIt = 0 ; bIt < a.child_bones.size() ; ++bIt ) {
                    unsigned int bcIdx = a.child_bones[bIt];
                    Bone & bc = bones[bcIdx];
                    bc.setFatherBone( bfIdx );
                    bf.child_bones.push_back( bcIdx );
                }
            }
        }

        for( unsigned int rIt = 0 ; rIt < rootBones.size() ; ++rIt ) {
            unsigned int rootboneIdx = rootBones[rIt];
            std::queue< unsigned int > bones_indices;
            bones_indices.push(rootboneIdx);
            while( ! bones_indices.empty()) {
                unsigned int bIdx = bones_indices.front();
                bones_indices.pop();
                ordered_bone_indices.push_back(bIdx);
                Bone & b = bones[bIdx];
                for( unsigned int bIt = 0 ; bIt < b.child_bones.size() ; ++bIt ) {
                    unsigned int bcIdx = b.child_bones[bIt];
                    bones_indices.push(bcIdx);
                }
            }
        }

        assert( ordered_bone_indices.size() == bones.size() );
    }

    void load (const std::string & filename){
        std::ifstream in (filename.c_str ());
        if (!in)
            exit (EXIT_FAILURE);
        std::string tmpString;
        unsigned int sizeA;
        in >> tmpString >> sizeA;
        articulations.resize (sizeA);
        for (unsigned int i = 0; i < sizeA; i++)
            in >> articulations[i].position[0] >> articulations[i].position[1] >> articulations[i].position[2];

        unsigned int sizeB;
        in >> tmpString >> sizeB;
        bones.resize (sizeB);
        for (unsigned int i = 0; i < sizeB; i++) {
            for (unsigned int j = 0; j < 2; j++)
                in >> bones[i].joints[j];
        }
        in.close ();

        buildStructure();
    }






    void computeGlobalTransformationParameters( SkeletonTransformation & transfo ) {
        std::vector< Vec3 > & articulations_transformed_position = transfo.articulations_transformed_position;
        articulations_transformed_position.resize( articulations.size() );
        for( unsigned int bIt = 0 ; bIt < ordered_bone_indices.size() ; ++bIt ) {
            unsigned bIdx = ordered_bone_indices[bIt];
            Bone & b = bones[bIdx];

            if( b.isRoot() ) {
                Vec3 a0RestPos = articulations[ b.joints[0] ].position;
                Vec3 a0TargetPos = a0RestPos;
                articulations_transformed_position[ b.joints[0] ] = a0TargetPos;
                BoneTransformation & bTransfo = transfo.bone_transformations[bIdx];
                bTransfo.world_space_rotation = bTransfo.local_rotation;

                // set the articulation as pivot point :
                bTransfo.world_space_translation = a0TargetPos - bTransfo.world_space_rotation * a0RestPos;

                // update the child articulation :
                Vec3 a1RestPos = articulations[ b.joints[1] ].position;
                Vec3 a1TargetPos = bTransfo.world_space_rotation * a1RestPos + bTransfo.world_space_translation;
                articulations_transformed_position[ b.joints[1] ] = a1TargetPos;
            }
            else{
                Vec3 a0RestPos = articulations[ b.joints[0] ].position;
                Vec3 a0TargetPos = articulations_transformed_position[ b.joints[0] ];

                BoneTransformation & bTransfo = transfo.bone_transformations[bIdx];
                BoneTransformation & bFatherTransfo = transfo.bone_transformations[b.father_bone];
                bTransfo.world_space_rotation = bFatherTransfo.world_space_rotation * bTransfo.local_rotation;

                // set the articulation as pivot point :
                bTransfo.world_space_translation = a0TargetPos - bTransfo.world_space_rotation * a0RestPos;

                // update the child articulation :
                Vec3 a1RestPos = articulations[ b.joints[1] ].position;
                Vec3 a1TargetPos = bTransfo.world_space_rotation * a1RestPos + bTransfo.world_space_translation;
                articulations_transformed_position[ b.joints[1] ] = a1TargetPos;
            }
        }
    }




    void computeProceduralAnimation( double t , SkeletonTransformation & transfo ) {
        transfo.bone_transformations.resize( bones.size() );
        for( unsigned int bIt = 0 ; bIt < ordered_bone_indices.size() ; ++bIt ) {
            unsigned bIdx = ordered_bone_indices[bIt];
            Bone & b = bones[bIdx];
            if( b.isRoot() ) {
                BoneTransformation & bTransfo = transfo.bone_transformations[bIdx];
                bTransfo.local_rotation = Mat3::Identity();
            }
            else{
                BoneTransformation & bTransfo = transfo.bone_transformations[bIdx];
                Vec3 axis( cos( 2 * M_PI * bIt / (double)(bones.size()) )  ,  sin( 2 * M_PI * bIt / (double)(bones.size()) )  , 0.0 );
                bTransfo.local_rotation = Mat3::getRotationMatrixFromAxisAndAngle( axis , (0.25*M_PI) * cos( t ) );
            }
        }

        // update articulation positions:
        computeGlobalTransformationParameters(transfo);
    }


    //----------------------------------------------//
    //----------------------------------------------//
    //----------------------------------------------//
    // draw functions :
    //----------------------------------------------//
    //----------------------------------------------//
    //----------------------------------------------//
    void draw( int displayed_bone ) const {
        glDisable(GL_LIGHTING);
        glDisable(GL_DEPTH);
        glDisable(GL_DEPTH_TEST);
        glLineWidth(3.0);
        glBegin (GL_LINES);
        for (unsigned int i = 0; i < bones.size (); i++) {
            glColor3f(1,0,0);
            {
                const Articulation & v = articulations[bones[i].joints[0]];
                glVertex3f (v.position[0], v.position[1], v.position[2]);
            }
            glColor3f(1,1,1);
            {
                const Articulation & v = articulations[bones[i].joints[1]];
                glVertex3f (v.position[0], v.position[1], v.position[2]);
            }
        }
        glEnd ();

        // we highlight the ordered bone number displayed_bone
        if( displayed_bone >= 0 && displayed_bone < ordered_bone_indices.size() ) {
            displayed_bone = ordered_bone_indices[displayed_bone];
            glLineWidth(8.0);
            glBegin (GL_LINES);
            glColor3f(1,0,0);
            {
                const Articulation & v = articulations[bones[displayed_bone].joints[0]];
                glVertex3f (v.position[0], v.position[1], v.position[2]);
            }
            glColor3f(1,0,0);
            {
                const Articulation & v = articulations[bones[displayed_bone].joints[1]];
                glVertex3f (v.position[0], v.position[1], v.position[2]);
            }
            glEnd ();
        }

        // draw articulations:
        glPointSize(12.0);
        glBegin(GL_POINTS);
        glColor3f(0.5,0,0);
        for (unsigned int i = 0; i < articulations.size (); i++) {
                const Articulation & v = articulations[i];
                glVertex3f (v.position[0], v.position[1], v.position[2]);
        }
        glEnd();
        glEnable(GL_DEPTH);
        glEnable(GL_DEPTH_TEST);
    }

    void drawTransformedSkeleton( int displayed_bone , SkeletonTransformation const & transfo ) const {
        glDisable(GL_LIGHTING);
        glDisable(GL_DEPTH);
        glDisable(GL_DEPTH_TEST);
        glLineWidth(3.0);
        glBegin (GL_LINES);
        for (unsigned int i = 0; i < bones.size (); i++) {
            glColor3f(1,0,0);
            {
                Vec3 p = transfo.articulations_transformed_position[ bones[i].joints[0] ];
                glVertex3f (p[0], p[1], p[2]);
            }
            glColor3f(1,1,1);
            {
                Vec3 p = transfo.articulations_transformed_position[ bones[i].joints[1] ];
                glVertex3f (p[0], p[1], p[2]);
            }
        }
        glEnd ();

        // we highlight the ordered bone number displayed_bone
        if( displayed_bone >= 0 && displayed_bone < ordered_bone_indices.size() ) {
            displayed_bone = ordered_bone_indices[displayed_bone];
            glLineWidth(8.0);
            glBegin (GL_LINES);
            glColor3f(1,0,0);
            {
                Vec3 p = transfo.articulations_transformed_position[ bones[displayed_bone].joints[0] ];
                glVertex3f (p[0], p[1], p[2]);
            }
            glColor3f(1,0,0);
            {
                Vec3 p = transfo.articulations_transformed_position[ bones[displayed_bone].joints[1] ];
                glVertex3f (p[0], p[1], p[2]);
            }
            glEnd ();
        }

        // draw articulations:
        glPointSize(12.0);
        glBegin(GL_POINTS);
        glColor3f(0.5,0,0);
        for (unsigned int i = 0; i < articulations.size (); i++) {
            Vec3 p = transfo.articulations_transformed_position[ i ];
            glVertex3f (p[0], p[1], p[2]);
        }
        glEnd();
        glEnable(GL_DEPTH);
        glEnable(GL_DEPTH_TEST);
    }
};



#endif // SKELETON_H

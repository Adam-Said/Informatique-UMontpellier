#ifndef linearSystem_H
#define linearSystem_H



#include <Eigen/Core>
#include <Eigen/SparseCore>
#include <Eigen/SparseCholesky>


#include <MatOp/SparseGenMatProd.h>
#include <MatOp/SparseSymShiftSolve.h>
#include <GenEigsSolver.h>
#include <SymEigsShiftSolver.h>
#include <SymGEigsSolver.h>
#include <MatOp/SparseCholesky.h>

using namespace Spectra;

#include <vector>
#include <map>


class linearSystem {
    std::vector< std::map< unsigned int , double > > _ASparse;

    Eigen::SparseMatrix< double > _A , _At , _AtA;
    Eigen::SimplicialLDLT< Eigen::SparseMatrix< double > > _AtA_choleskyDecomposition;

    Eigen::VectorXd _b;

    unsigned int _rows , _columns;

public:
    linearSystem() {
        _rows = _columns = 0;
    }
    linearSystem( int rows , int columns ) {
        setDimensions(rows , columns);
    }
    ~linearSystem() {
    }

    void setDimensions( int rows , int columns ) {
        _rows = rows; _columns = columns;
        _ASparse.clear();
        _ASparse.resize(_rows);
        _b.resize(_rows);
    }

    double & A(unsigned int row , unsigned int column) {
        return _ASparse[row][column];
    }

    double & b(unsigned int row) {
        return _b[ row ];
    }

    void preprocess() {
        // convert ad-hoc matrix to Eigen sparse format:
        {
            _A.resize(_rows , _columns);
            std::vector< Eigen::Triplet< double > > triplets;
            for( unsigned int r = 0 ; r < _rows ; ++r ) {
                for( std::map< unsigned int , double >::const_iterator it = _ASparse[r].begin() ; it != _ASparse[r].end() ; ++it ) {
                    unsigned int c = it->first;
                    double val = it->second;
                    triplets.push_back( Eigen::Triplet< double >(r,c,val) );
                }
            }
            _A.setFromTriplets( triplets.begin() , triplets.end() );
        }
        _At = _A.transpose();
        _AtA = _At * _A;
        _AtA_choleskyDecomposition.analyzePattern(_AtA);
        _AtA_choleskyDecomposition.compute(_AtA);
    }

    void solve( Eigen::VectorXd & X ) {
        X = _AtA_choleskyDecomposition.solve( _At * _b );
    }



    template< class vector_t >
    Eigen::VectorXd multiplyAby( vector_t const & X) const {
        Eigen::VectorXd AX(_rows);
        for( unsigned int row = 0 ; row < _rows ; ++row ) AX[row] = 0.0;

        for( unsigned int row = 0 ; row < _rows ; ++row ) {
            std::map< unsigned int , double > const & currentRow = _ASparse[row];
            for( std::map< unsigned int , double >::const_iterator it = currentRow.begin() ; it != currentRow.end() ; ++it ) {
                unsigned int column = it->first ; float_t value = it->second;
                AX[row] += value * X[column];
            }
        }
        return AX;
    }




};

#endif // linearSystem_H

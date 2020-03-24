
/*************************************************
 * Counting inversions as seen in the class (the divide and conquer approach)
 *give the array, count how many inverse number in the array. 
 * ***********************************************/
//divide and conquer approach
//it the merge sort

 public class CountingInversion {

     //global variable count to pass the count Inverse.
    public static int count=0;

    /*************************************
     *method for sort(divide)
     *************************************/
    private static int[] mergeSort(int[] array){

        //recursive function base
        if(array.length <= 1) {
            return array;
        }
        //define the mid point
        int midpoint = array.length / 2;

        //define the left and right array(here need to check the right array length
        // if the origin array length is odd or even)
        int[] leftArray = new int[midpoint];
        int[] rightArray;
        if(array.length % 2 == 0) {
            rightArray = new int[midpoint];
        } else {
            rightArray = new int[midpoint+1];
        }

        //give the left and right array value
        for(int i=0; i < leftArray.length; i++){
            leftArray[i] = array[i];
        }
        for(int j=0; j < rightArray.length; j++){
            rightArray[j] = array[midpoint+j];
        }

        //recursive call
        leftArray = mergeSort(leftArray);
        rightArray = mergeSort(rightArray);


        //create new array for the result
        int[] mergeArray = new int[array.length];
        mergeArray = mergeCount(leftArray, rightArray);

        return mergeArray;
    }

    /*************************************
     *method for merge
     **************************************/
    private static int[] mergeCount(int[] leftArray, int[] rightArray) {
        //create the new array, and the length is the same as the passed
        //rightArray length + leftArray length
        int[] mergeArray = new int[leftArray.length + rightArray.length];

        //initial each array pointer
        int leftPointer = 0;
        int rightPointer = 0;
        int resultPointer = 0;

        while(leftPointer < leftArray.length || rightPointer < rightArray.length){
            //if the both passed array have elements, check both
            if(leftPointer < leftArray.length && rightPointer < rightArray.length){
                //compare the leftArray with rightArray
                if(leftArray[leftPointer] > rightArray[rightPointer]) {
                    //the rightArray pointer move
                    mergeArray[resultPointer++] = rightArray[rightPointer++];
                    //when merger left array and right array together, array already sorted
                    //if the leftArray elements L > rightArray elements R
                    //then after elements L all the elements in leftArray > this right elements R
                    count = count + (leftArray.length-leftPointer) ;
                }else{
                    //the leftArray pointer move
                    mergeArray[resultPointer++] = leftArray[leftPointer++];
                }
            }else if(leftPointer < leftArray.length) { //only in the left array
                mergeArray[resultPointer++] = leftArray[leftPointer++];

            }else if(rightPointer < rightArray.length){ //only in the right array
                mergeArray[resultPointer++] = rightArray[rightPointer++];
            }
        }//end while
        return mergeArray;
    }

    /*************************************
     *method for print array
     *************************************/
    private static void printArray(int array[]){
        for(int i: array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /*************************************
     * main function
     *************************************/
    public static void main(String args[]) {
        int[] array = {1, 5, 4, 8, 10, 2, 6, 9, 12, 11, 3, 7};
        //{3, 7, 10, 14, 18, 19, 2, 11, 16, 17, 23, 25};
        System.out.println("Initial Array: ");
        printArray(array);

        array = mergeSort(array);
        System.out.println("MergeSorted Array: ");
        printArray(array);
        System.out.println("\nThe total inversions : " + count);

    }
}

package 第一章_背包_队列和栈;

import java.io.*;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_1_3_43 {
	static class Queue<T> {
		private T[] items = (T[])new Object[1];
		private int head;
		private int tail;
		private int size;
		void resize(int newsize) {
			T[] newItems = (T[])new Object[newsize];
			int cur = head, k = 0;
			do {
				newItems[k++] = items[cur++];
				if (cur == items.length) 
					cur = 0;
			} while(cur != tail);
			head = 0;
			tail = size;
			items = newItems;
		}
		int size() { return size; };
		void enqueue(T item) {
			if (size == items.length)
				resize(2 * size);
			size++;
			items[tail++] = item;
			if (tail == items.length) 
				tail = 0;
		}
		T dequeue() {
			if (isEmpty())
				throw new RuntimeException("dequeue from a empty queue");
			size--;
			T del = items[head];
			items[head++] = null;
			if (head == items.length)
				head = 0;
			if (size > 0 && size == items.length / 4)
				resize(items.length / 2);
			return del;
		}
		T peek() {
			return items[head];
		}
		boolean isEmpty() { return size == 0; }
		public String toString() {
			if (isEmpty()) return "[empty]";
			StringBuilder sb = new StringBuilder();
			sb.append("|");
			for (T item : items)
				sb.append(String.format(" %2s |", item == null ? " " : item));
			return sb.toString();
		}
	}
	
	
	/*
	 * recursively print
	 */
	public static void printByRecursivion(File file) {
		StdOut.println("====== Recursivion ======");
		int depth = 0;
		print(file, depth);
	}
	public static void print(File file, int traceDepth) {
		if (!file.isDirectory()) {
			StdOut.println(fileString(file, traceDepth));
			return;
		}
		StdOut.println(fileString(file, traceDepth));
		for(File f : file.listFiles()) {
			traceDepth++;
			print(f, traceDepth);
			traceDepth--;
		}
	}
	public static String fileString(File file, int depth) {
		String str = "";
		for(int i = 0; i < depth; i++)
			str += "     ";
		return  str + "- " + file.getName();
	}
	
	/*
	 * implemented by queue
	 */
	public static void printByQueue(File file) {
		StdOut.println("\n\n\n====== Queue ======");
		Queue<File> queue = new Queue<File>();
		queue.enqueue(file);
		while(!queue.isEmpty()) {
			File f = queue.dequeue();
			if (!f.isDirectory()) {				
				StdOut.println(f.getName());
			} else {
				StdOut.println(f.getName());
				for(File ff : f.listFiles()) {
					queue.enqueue(ff);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		 File file = new File("/Users/bot/Desktop/ThinkingInJava");
		 printByRecursivion(file);
		 printByQueue(file);
	}
	// output
	/*
	 * 	====== Recursivion ======
		- ThinkingInJava
		     - .DS_Store
		     - Java编程思想(第4版).pdf
		     - The_Thinking_in_Java_Annotated_Solution_Guide(TIJ4-solutions).pdf
		     - Thinking in Java 第4版 源代码
		          - .DS_Store
		          - access
		               - build.xml
		               - Cake.java
		               - ChocolateChip.java
		               - ChocolateChip2.java
		               - cookie2
		                    - Cookie.java
		               - dessert
		                    - Cookie.java
		               - Dinner.java
		               - FullQualification.java
		               - IceCream.java
		               - ImportedMyClass.java
		               - LibTest.java
		               - Lunch.java
		               - mypackage
		                    - MyClass.java
		               - OrganizedByAccess.java
		               - Pie.java
		               - PrintTest.java
		               - QualifiedMyClass.java
		               - SingleImport.java
		          - annotations
		               - AtUnitComposition.java
		               - AtUnitExample1.java
		               - AtUnitExample2.java
		               - AtUnitExample3.java
		               - AtUnitExample4.java
		               - AtUnitExample5.java
		               - AtUnitExternalTest.java
		               - build.xml
		               - database
		                    - Constraints.java
		                    - DBTable.java
		                    - Member.java
		                    - SQLInteger.java
		                    - SQLString.java
		                    - TableCreationProcessorFactory.java
		                    - TableCreator.java
		                    - Uniqueness.java
		               - ExtractInterface.java
		               - HashSetTest.java
		               - InterfaceExtractorProcessor.java
		               - InterfaceExtractorProcessorFactory.java
		               - Multiplier.java
		               - PasswordUtils.java
		               - SimulatingNull.java
		               - StackL.java
		               - StackLStringTest.java
		               - Testable.java
		               - UseCase.java
		               - UseCaseTracker.java
		          - arrays
		               - AlphabeticSearch.java
		               - ArrayOfGenerics.java
		               - ArrayOfGenericType.java
		               - ArrayOptions.java
		               - ArraySearching.java
		               - AssemblingMultidimensionalArrays.java
		               - AutoboxingArrays.java
		               - build.xml
		               - ComparatorTest.java
		               - ComparingArrays.java
		               - CompType.java
		               - ContainerComparison.java
		               - CopyingArrays.java
		               - FillingArrays.java
		               - GeneratorsTest.java
		               - IceCream.java
		               - MultidimensionalObjectArrays.java
		               - MultidimensionalPrimitiveArray.java
		               - MultiDimWrapperArray.java
		               - ParameterizedArrayType.java
		               - PrimitiveConversionDemonstration.java
		               - RaggedArray.java
		               - RandomGeneratorsTest.java
		               - Reverse.java
		               - StringSorting.java
		               - TestArrayGeneration.java
		               - TestGenerated.java
		               - ThreeDWithNew.java
		          - bangbean
		               - BangBean.java
		               - BangBeanTest.java
		               - build.xml
		          - build.xml
		          - chapterOrder.xml
		          - concurrency
		               - .DS_Store
		               - ActiveObjectDemo.java
		               - AtomicEvenGenerator.java
		               - AtomicIntegerTest.java
		               - Atomicity.java
		               - AtomicityTest.java
		               - AttemptLocking.java
		               - BankTellerSimulation.java
		               - BasicThreads.java
		               - build.xml
		               - CachedThreadPool.java
		               - CallableDemo.java
		               - CaptureUncaughtException.java
		               - CarBuilder.java
		               - Chopstick.java
		               - CloseResource.java
		               - CountDownLatchDemo.java
		               - CriticalSection.java
		               - DaemonFromFactory.java
		               - Daemons.java
		               - DaemonsDontRunFinally.java
		               - DeadlockingDiningPhilosophers.java
		               - DelayQueueDemo.java
		               - EvenChecker.java
		               - EvenGenerator.java
		               - ExceptionThread.java
		               - ExchangerDemo.java
		               - ExplicitCriticalSection.java
		               - FastSimulation.java
		               - Fat.java
		               - FixedDiningPhilosophers.java
		               - FixedThreadPool.java
		               - GreenhouseScheduler.java
		               - HorseRace.java
		               - Interrupting.java
		               - Interrupting2.java
		               - InterruptingIdiom.java
		               - IntGenerator.java
		               - Joining.java
		               - LiftOff.java
		               - ListComparisons.java
		               - MainThread.java
		               - MapComparisons.java
		               - MoreBasicThreads.java
		               - MultiLock.java
		               - MutexEvenGenerator.java
		               - NaiveExceptionHandling.java
		               - NIOInterruption.java
		               - NotifyVsNotifyAll.java
		               - OrnamentalGarden.java
		               - Philosopher.java
		               - PipedIO.java
		               - Pool.java
		               - PriorityBlockingQueueDemo.java
		               - ReaderWriterList.java
		               - ResponsiveUI.java
		               - Restaurant.java
		               - restaurant2
		                    - RestaurantWithQueues.java
		               - SelfManaged.java
		               - SemaphoreDemo.java
		               - SerialNumberChecker.java
		               - SerialNumberGenerator.java
		               - SettingDefaultHandler.java
		               - SimpleDaemons.java
		               - SimpleMicroBenchmark.java
		               - SimplePriorities.java
		               - SimpleThread.java
		               - SingleThreadExecutor.java
		               - SleepingTask.java
		               - SynchronizationComparisons.java
		               - SynchronizedEvenGenerator.java
		               - SyncObject.java
		               - TestBlockingQueues.java
		               - Tester.java
		               - ThreadLocalVariableHolder.java
		               - ThreadVariations.java
		               - ToastOMatic.java
		               - waxomatic
		                    - WaxOMatic.java
		               - waxomatic2
		                    - WaxOMatic2.java
		          - containers
		               - AssociativeArray.java
		               - Bits.java
		               - build.xml
		               - CanonicalMapping.java
		               - CollectionDataGeneration.java
		               - CollectionDataTest.java
		               - CollectionMethods.java
		               - CountedString.java
		               - DequeTest.java
		               - Enumerations.java
		               - FailFast.java
		               - FillingLists.java
		               - Groundhog.java
		               - Groundhog2.java
		               - IndividualTest.java
		               - LinkedHashMapDemo.java
		               - ListPerformance.java
		               - Lists.java
		               - ListSortSearch.java
		               - MapDataTest.java
		               - MapEntry.java
		               - MapPerformance.java
		               - Maps.java
		               - Prediction.java
		               - QueueBehavior.java
		               - RandomBounds.java
		               - ReadOnly.java
		               - References.java
		               - SetPerformance.java
		               - SimpleHashMap.java
		               - SlowMap.java
		               - SortedMapDemo.java
		               - SortedSetDemo.java
		               - SpringDetector.java
		               - SpringDetector2.java
		               - Stacks.java
		               - StringHashCode.java
		               - Synchronization.java
		               - Test.java
		               - Tester.java
		               - TestParam.java
		               - ToDoList.java
		               - TypesForSets.java
		               - Unsupported.java
		               - Utilities.java
		          - control
		               - BreakAndContinue.java
		               - build.xml
		               - CommaOperator.java
		               - ForEachFloat.java
		               - ForEachInt.java
		               - ForEachString.java
		               - IfElse.java
		               - IfElse2.java
		               - LabeledFor.java
		               - LabeledWhile.java
		               - ListCharacters.java
		               - VowelsAndConsonants.java
		               - WhileTest.java
		          - Copyright.txt
		          - DEclipse.py
		          - Eclipse.py
		          - enumerated
		               - AlarmPoints.java
		               - BigEnumSet.java
		               - build.xml
		               - Burrito.java
		               - cartoons
		                    - EnumImplementation.java
		               - CarWash.java
		               - Competitor.java
		               - ConstantSpecificMethod.java
		               - EnumClass.java
		               - EnumMaps.java
		               - EnumSets.java
		               - Input.java
		               - menu
		                    - Course.java
		                    - Food.java
		                    - Meal.java
		                    - Meal2.java
		                    - TypeOfFood.java
		               - NonEnum.java
		               - NotClasses.java
		               - Outcome.java
		               - OverrideConstantSpecific.java
		               - OzWitch.java
		               - PostOffice.java
		               - RandomTest.java
		               - Reflection.java
		               - RoShamBo.java
		               - RoShamBo1.java
		               - RoShamBo2.java
		               - RoShamBo3.java
		               - RoShamBo4.java
		               - RoShamBo5.java
		               - RoShamBo6.java
		               - SecurityCategory.java
		               - SpaceShip.java
		               - Spiciness.java
		               - TrafficLight.java
		               - UpcastEnum.java
		               - VendingMachine.java
		               - VendingMachineInput.txt
		          - exceptions
		               - AlwaysFinally.java
		               - build.xml
		               - Cleanup.java
		               - CleanupIdiom.java
		               - DynamicFields.java
		               - ExceptionMethods.java
		               - ExceptionSilencer.java
		               - ExtraFeatures.java
		               - FinallyWorks.java
		               - FullConstructors.java
		               - Human.java
		               - InheritingExceptions.java
		               - InputFile.java
		               - LoggingExceptions.java
		               - LoggingExceptions2.java
		               - LostMessage.java
		               - MainException.java
		               - MultipleReturns.java
		               - NeverCaught.java
		               - OnOffException1.java
		               - OnOffException2.java
		               - OnOffSwitch.java
		               - Rethrowing.java
		               - RethrowNew.java
		               - StormyInning.java
		               - Switch.java
		               - TurnOffChecking.java
		               - WhoCalled.java
		               - WithFinally.java
		          - FindBugsExcluder.py
		          - FindBugsFilter.xml
		          - frogbean
		               - build.xml
		               - Frog.java
		          - generics
		               - Apply.java
		               - ArrayMaker.java
		               - ArrayOfGeneric.java
		               - ArrayOfGenericReference.java
		               - BankTeller.java
		               - BasicBounds.java
		               - BasicGeneratorDemo.java
		               - BasicHolder.java
		               - build.xml
		               - ByteSet.java
		               - CaptureConversion.java
		               - CheckedList.java
		               - ClassCasting.java
		               - ClassTypeCapture.java
		               - coffee
		                    - Americano.java
		                    - Breve.java
		                    - Cappuccino.java
		                    - Coffee.java
		                    - CoffeeGenerator.java
		                    - Latte.java
		                    - Mocha.java
		               - ComparablePet.java
		               - CompilerIntelligence.java
		               - CountedObject.java
		               - CovariantArrays.java
		               - CovariantReturnTypes.java
		               - CreatorGeneric.java
		               - CRGWithBasicHolder.java
		               - CuriouslyRecurringGeneric.java
		               - decorator
		                    - Decoration.java
		               - DogsAndRobots.cpp
		               - DogsAndRobots.java
		               - DynamicProxyMixin.java
		               - EpicBattle.java
		               - Erased.java
		               - ErasedTypeEquivalence.java
		               - ErasureAndInheritance.java
		               - ExplicitTypeSpecification.java
		               - FactoryConstraint.java
		               - Fibonacci.java
		               - Fill.java
		               - Fill2.java
		               - FilledListMaker.java
		               - Functional.java
		               - Generators.java
		               - GenericArray.java
		               - GenericArray2.java
		               - GenericArrayWithTypeToken.java
		               - GenericCast.java
		               - GenericHolder.java
		               - GenericMethods.java
		               - GenericReading.java
		               - GenericsAndCovariance.java
		               - GenericsAndReturnTypes.java
		               - GenericVarargs.java
		               - GenericWriting.java
		               - HasF.java
		               - HijackedInterface.java
		               - Holder.java
		               - Holder1.java
		               - Holder2.java
		               - Holder3.java
		               - InheritBounds.java
		               - InstantiateGenericType.cpp
		               - InstantiateGenericType.java
		               - IterableFibonacci.java
		               - LatentReflection.java
		               - LimitsOfInference.java
		               - LinkedStack.java
		               - ListMaker.java
		               - ListOfGenerics.java
		               - ListOfInt.java
		               - LostInformation.java
		               - Manipulation.java
		               - Manipulator2.java
		               - Manipulator3.java
		               - Mixins.cpp
		               - Mixins.java
		               - MultipleInterfaceVariants.java
		               - NeedCasting.java
		               - NonCovariantGenerics.java
		               - NotSelfBounded.java
		               - OrdinaryArguments.java
		               - Performs.java
		               - PlainGenericInheritance.java
		               - PrimitiveGenericTest.java
		               - RandomList.java
		               - RestrictedComparablePets.java
		               - ReturnGenericType.java
		               - SelfBounding.java
		               - SelfBoundingAndCovariantArguments.java
		               - SelfBoundingMethods.java
		               - SimpleDogsAndRobots.java
		               - SimpleHolder.java
		               - SimpleQueue.java
		               - SimplerPets.java
		               - Store.java
		               - SuperTypeWildcards.java
		               - Templates.cpp
		               - ThrowGenericException.java
		               - TupleList.java
		               - TupleTest.java
		               - TupleTest2.java
		               - UnboundedWildcards1.java
		               - UnboundedWildcards2.java
		               - Unconstrained.java
		               - UseList.java
		               - UseList2.java
		               - watercolors
		                    - Watercolors.java
		               - WatercolorSets.java
		               - Wildcards.java
		          - gui
		               - BangBean2.java
		               - BeanDumper.java
		               - BorderLayout1.java
		               - Borders.java
		               - build.xml
		               - Button1.java
		               - Button2.java
		               - Button2b.java
		               - ButtonGroups.java
		               - Buttons.java
		               - CheckBoxes.java
		               - ColorBoxes.java
		               - ComboBoxes.java
		               - Dialogs.java
		               - Face0.gif
		               - Face1.gif
		               - Face2.gif
		               - Face3.gif
		               - Face4.gif
		               - Faces.java
		               - FileChooserTest.java
		               - flex
		                    - build-command.txt
		                    - helloflex1.mxml
		                    - helloflex2.mxml
		                    - Song.java
		                    - songs.mxml
		                    - songScript.as
		                    - SongService.java
		                    - songStyles.css
		               - FlowLayout1.java
		               - GridLayout1.java
		               - HelloLabel.java
		               - HelloSwing.java
		               - HTMLButton.java
		               - InterruptableLongRunningCallable.java
		               - InterruptableLongRunningTask.java
		               - jnlp
		                    - filechooser.html
		                    - filechooser.jnlp
		                    - JnlpFileChooser.java
		                    - mindview.gif
		               - List.java
		               - LongRunningTask.java
		               - LookAndFeel.java
		               - Menus.java
		               - MessageBoxes.java
		               - MonitoredLongRunningCallable.java
		               - Popup.java
		               - Progress.java
		               - RadioButtons.java
		               - ShowAddListeners.java
		               - SimpleMenus.java
		               - SineWave.java
		               - SubmitLabelManipulationTask.java
		               - SubmitSwingProgram.java
		               - TabbedPane1.java
		               - TextArea.java
		               - TextFields.java
		               - TextPane.java
		               - TicTacToe.java
		               - TrackEvent.java
		          - holding
		               - AdapterMethodIdiom.java
		               - AddingGroups.java
		               - ApplesAndOrangesWithGenerics.java
		               - ApplesAndOrangesWithoutGenerics.java
		               - ArrayIsNotIterable.java
		               - AsListInference.java
		               - build.xml
		               - CollectionSequence.java
		               - ContainerMethods.java
		               - CrossContainerIteration.java
		               - EnvironmentVariables.java
		               - ForEachCollections.java
		               - GenericsAndUpcasting.java
		               - InterfaceVsIterator.java
		               - IterableClass.java
		               - LinkedListFeatures.java
		               - ListFeatures.java
		               - ListIteration.java
		               - MapOfList.java
		               - ModifyingArraysAsList.java
		               - MultiIterableClass.java
		               - NonCollectionSequence.java
		               - PetMap.java
		               - PrintingContainers.java
		               - PriorityQueueDemo.java
		               - QueueDemo.java
		               - SetOfInteger.java
		               - SetOperations.java
		               - SimpleCollection.java
		               - SimpleIteration.java
		               - SortedSetOfInteger.java
		               - StackCollision.java
		               - StackTest.java
		               - Statistics.java
		               - UniqueWords.java
		               - UniqueWordsAlphabetic.java
		          - initialization
		               - Apricot.java
		               - ArrayClassObj.java
		               - ArrayInit.java
		               - ArrayNew.java
		               - ArraysOfPrimitives.java
		               - AutoboxingVarargs.java
		               - BananaPeel.java
		               - build.xml
		               - Burrito.java
		               - Counter.java
		               - DefaultConstructor.java
		               - Demotion.java
		               - DynamicArray.java
		               - EnumOrder.java
		               - ExplicitStatic.java
		               - Flower.java
		               - InitialValues.java
		               - InitialValues2.java
		               - Leaf.java
		               - Measurement.java
		               - MethodInit.java
		               - MethodInit2.java
		               - MethodInit3.java
		               - Mugs.java
		               - NewVarArgs.java
		               - NoSynthesis.java
		               - OptionalTrailingArguments.java
		               - OrderOfInitialization.java
		               - Overloading.java
		               - OverloadingOrder.java
		               - OverloadingVarargs.java
		               - OverloadingVarargs2.java
		               - OverloadingVarargs3.java
		               - PassingThis.java
		               - PrimitiveOverloading.java
		               - SimpleConstructor.java
		               - SimpleConstructor2.java
		               - SimpleEnumUse.java
		               - Spiciness.java
		               - Spoon.java
		               - StaticInitialization.java
		               - TerminationCondition.java
		               - VarArgs.java
		               - VarargType.java
		          - innerclasses
		               - AnonymousConstructor.java
		               - BigEgg.java
		               - BigEgg2.java
		               - build.xml
		               - Callbacks.java
		               - ClassInInterface.java
		               - Contents.java
		               - controller
		                    - Controller.java
		                    - Event.java
		               - Destination.java
		               - DotNew.java
		               - DotThis.java
		               - Factories.java
		               - Games.java
		               - GreenhouseController.java
		               - GreenhouseControls.java
		               - InheritInner.java
		               - LocalInnerClass.java
		               - MultiImplementation.java
		               - MultiInterfaces.java
		               - MultiNestingAccess.java
		               - Parcel1.java
		               - Parcel10.java
		               - Parcel11.java
		               - Parcel2.java
		               - Parcel3.java
		               - Parcel5.java
		               - Parcel6.java
		               - Parcel7.java
		               - Parcel7b.java
		               - Parcel8.java
		               - Parcel9.java
		               - Sequence.java
		               - TestBed.java
		               - TestParcel.java
		               - Wrapping.java
		          - interfaces
		               - AdaptedRandomDoubles.java
		               - Adventure.java
		               - build.xml
		               - classprocessor
		                    - Apply.java
		               - Factories.java
		               - filters
		                    - BandPass.java
		                    - Filter.java
		                    - HighPass.java
		                    - LowPass.java
		                    - Waveform.java
		               - Games.java
		               - HorrorShow.java
		               - InterfaceCollision.java
		               - interfaceprocessor
		                    - Apply.java
		                    - FilterProcessor.java
		                    - Processor.java
		                    - StringProcessor.java
		               - Months.java
		               - music4
		                    - Music4.java
		               - music5
		                    - Music5.java
		               - nesting
		                    - NestingInterfaces.java
		               - RandomDoubles.java
		               - RandomWords.java
		               - RandVals.java
		               - TestRandVals.java
		          - io
		               - Alien.java
		               - AvailableCharSets.java
		               - BasicFileOutput.java
		               - Blip3.java
		               - Blips.java
		               - BufferedInputFile.java
		               - BufferToText.java
		               - build.xml
		               - ChangeSystemOut.java
		               - ChannelCopy.java
		               - DirectoryDemo.java
		               - DirList.java
		               - DirList2.java
		               - DirList3.java
		               - Echo.java
		               - Endians.java
		               - FileLocking.java
		               - FileOutputShortcut.java
		               - FormattedMemoryInput.java
		               - FreezeAlien.java
		               - GetChannel.java
		               - GetData.java
		               - GZIPcompress.java
		               - IntBufferDemo.java
		               - LargeMappedFiles.java
		               - LockingMappedFiles.java
		               - Logon.java
		               - MakeDirectories.java
		               - MappedIO.java
		               - MemoryInput.java
		               - MyWorld.java
		               - OSExecuteDemo.java
		               - PreferencesDemo.java
		               - RecoverCADState.java
		               - Redirecting.java
		               - SerialCtl.java
		               - StoreCADState.java
		               - StoringAndRecoveringData.java
		               - TestEOF.java
		               - TransferTo.java
		               - UsingBuffers.java
		               - UsingRandomAccessFile.java
		               - ViewBuffers.java
		               - Worm.java
		               - xfiles
		                    - ThawAlien.java
		               - ZipCompress.java
		          - JavaLint.py
		          - net
		               - .DS_Store
		               - build.xml
		               - mindview
		                    - .DS_Store
		                    - atunit
		                         - AtUnit.java
		                         - AtUnitRemover.java
		                         - ClassNameFinder.java
		                         - Test.java
		                         - TestObjectCleanup.java
		                         - TestObjectCreate.java
		                         - TestProperty.java
		                    - simple
		                         - List.java
		                         - Vector.java
		                    - util
		                         - BasicGenerator.java
		                         - BinaryFile.java
		                         - CollectionData.java
		                         - ContainerMethodDifferences.java
		                         - ConvertTo.java
		                         - CountingGenerator.java
		                         - CountingIntegerList.java
		                         - CountingMapData.java
		                         - Countries.java
		                         - DaemonThreadFactory.java
		                         - DaemonThreadPoolExecutor.java
		                         - Deque.java
		                         - Directory.java
		                         - Enums.java
		                         - FiveTuple.java
		                         - FourTuple.java
		                         - Generated.java
		                         - Generator.java
		                         - Hex.java
		                         - MapData.java
		                         - New.java
		                         - Null.java
		                         - OSExecute.java
		                         - OSExecuteException.java
		                         - Pair.java
		                         - PPrint.java
		                         - Print.java
		                         - ProcessFiles.java
		                         - RandomGenerator.java
		                         - Range.java
		                         - Sets.java
		                         - Stack.java
		                         - SwingConsole.java
		                         - TaskItem.java
		                         - TaskManager.java
		                         - TextFile.java
		                         - ThreeTuple.java
		                         - Tuple.java
		                         - TwoTuple.java
		                         - TypeCounter.java
		          - object
		               - build.xml
		               - Documentation1.java
		               - Documentation2.java
		               - Documentation3.java
		               - HelloDate.java
		               - ShowProperties.java
		          - operators
		               - AllOps.java
		               - Assignment.java
		               - AutoInc.java
		               - BitManipulation.java
		               - Bool.java
		               - build.xml
		               - Casting.java
		               - CastingNumbers.java
		               - EqualsMethod.java
		               - EqualsMethod2.java
		               - Equivalence.java
		               - Exponents.java
		               - HelloDate.java
		               - Literals.java
		               - MathOps.java
		               - Overflow.java
		               - PassObject.java
		               - Precedence.java
		               - RoundingNumbers.java
		               - ShortCircuit.java
		               - StringOperators.java
		               - TernaryIfElse.java
		               - URShift.java
		          - OutputGenerator.py
		          - OutputVerifier.py
		          - polymorphism
		               - .DS_Store
		               - build.xml
		               - CovariantReturn.java
		               - FieldAccess.java
		               - Frog.java
		               - music
		                    - Instrument.java
		                    - Music.java
		                    - Music2.java
		                    - Note.java
		                    - Wind.java
		               - music3
		                    - Music3.java
		               - PolyConstructors.java
		               - PrivateOverride.java
		               - ReferenceCounting.java
		               - RTTI.java
		               - Sandwich.java
		               - shape
		                    - Circle.java
		                    - RandomShapeGenerator.java
		                    - Shape.java
		                    - Square.java
		                    - Triangle.java
		               - Shapes.java
		               - StaticPolymorphism.java
		               - Transmogrify.java
		          - RedundantImportDetector.py
		          - reusing
		               - Bath.java
		               - Beetle.java
		               - BlankFinal.java
		               - build.xml
		               - CADSystem.java
		               - Car.java
		               - Cartoon.java
		               - Chess.java
		               - Detergent.java
		               - FinalArguments.java
		               - FinalData.java
		               - FinalOverridingIllusion.java
		               - Hide.java
		               - Jurassic.java
		               - Lisa.java
		               - Orc.java
		               - PlaceSetting.java
		               - SpaceShip.java
		               - SpaceShipControls.java
		               - SpaceShipDelegation.java
		               - SprinklerSystem.java
		               - Wind.java
		          - strings
		               - ArrayListDisplay.java
		               - BetterRead.java
		               - build.xml
		               - Concatenation.java
		               - Conversion.java
		               - DatabaseException.java
		               - Finding.java
		               - Groups.java
		               - Immutable.java
		               - InfiniteRecursion.java
		               - IntegerMatch.java
		               - JGrep.java
		               - Receipt.java
		               - ReFlags.java
		               - Replacing.java
		               - ReplacingStringTokenizer.java
		               - Resetting.java
		               - Rudolph.java
		               - ScannerDelimiter.java
		               - SimpleFormat.java
		               - SimpleRead.java
		               - SplitDemo.java
		               - Splitting.java
		               - StartEnd.java
		               - TestRegularExpression.java
		               - TheReplacements.java
		               - ThreatAnalyzer.java
		               - Turtle.java
		               - UsingStringBuilder.java
		               - WhitherStringBuilder.java
		          - swt
		               - build.xml
		               - ColorBoxes.java
		               - DisplayEnvironment.java
		               - DisplayProperties.java
		               - HelloSWT.java
		               - Menus.java
		               - ShellsAreMainWindows.java
		               - SineWave.java
		               - TabbedPane.java
		               - util
		                    - SWTApplication.java
		                    - SWTConsole.java
		          - typeinfo
		               - AnonymousImplementation.java
		               - BoundedClassReferences.java
		               - build.xml
		               - ClassCasts.java
		               - ClassInitialization.java
		               - factory
		                    - Factory.java
		               - FamilyVsExactType.java
		               - FilledList.java
		               - GenericClassReferences.java
		               - HiddenImplementation.java
		               - InnerImplementation.java
		               - interfacea
		                    - A.java
		               - InterfaceViolation.java
		               - ModifyingPrivateFields.java
		               - NullRobot.java
		               - Operation.java
		               - packageaccess
		                    - HiddenC.java
		               - Person.java
		               - PetCount.java
		               - PetCount2.java
		               - PetCount3.java
		               - PetCount4.java
		               - pets
		                    - Cat.java
		                    - Cymric.java
		                    - Dog.java
		                    - EgyptianMau.java
		                    - ForNameCreator.java
		                    - Hamster.java
		                    - Individual.java
		                    - LiteralPetCreator.java
		                    - Manx.java
		                    - Mouse.java
		                    - Mutt.java
		                    - Person.java
		                    - Pet.java
		                    - PetCreator.java
		                    - Pets.java
		                    - Pug.java
		                    - Rat.java
		                    - Rodent.java
		               - Position.java
		               - RegisteredFactories.java
		               - Robot.java
		               - SelectingMethods.java
		               - Shapes.java
		               - ShowMethods.java
		               - SimpleDynamicProxy.java
		               - SimpleProxyDemo.java
		               - SnowRemovalRobot.java
		               - Staff.java
		               - SweetShop.java
		               - toys
		                    - GenericToyTest.java
		                    - ToyTest.java
		               - WildcardClassReferences.java
		          - xml
		               - build.xml
		               - People.java
		               - Person.java
		     - Thinking In Java 第四版(英文版)-练习题答案
		          - .DS_Store
		          - Access
		               - AccessTest.java
		               - Collision.java
		               - ConnectionManager.java
		               - CookieMonster.java
		               - CookieThief.java
		               - Debug.java
		               - Foreign.java
		               - MakeWidget.java
		               - ProtectedData.java
		               - UnpackagedMyClass.java
		          - Arrays
		               - ArrayOfGenerics10.java
		               - ContainerComparison15.java
		               - Ex1(2).java
		               - Ex11(5).java
		               - Ex12(3).java
		               - Ex13(2).java
		               - Ex14(2).java
		               - Ex17(2).java
		               - Ex18(3).java
		               - Ex19(3).java
		               - Ex2(3).java
		               - Ex20(2).java
		               - Ex21(2).java
		               - Ex22.java
		               - Ex23(1).java
		               - Ex24(1).java
		               - Ex25(2).java
		               - Ex3(2).java
		               - Ex4(4).java
		               - Ex5(3).java
		               - Ex6(4).java
		               - Ex7(2).java
		               - Ex8(4).java
		               - Ex9(3).java
		               - Sterilizer.java
		               - TestArrayGeneration16.java
		          - Containers
		               - CountingMapData5.java
		               - Ex1(3).java
		               - Ex10(4).java
		               - Ex11(4).java
		               - Ex12(2).java
		               - Ex2(4).java
		               - Ex3(3).java
		               - Ex4(3).java
		               - Ex7(3).java
		               - Ex8(5).java
		               - Ex9(4).java
		               - Maps14.java
		               - Unsupported6.java
		               - WordCounter13.java
		               - WordCounter15.java
		          - Control
		               - BitTest.java
		               - CompareInts.java
		               - CompareIntsForever.java
		               - Count.java
		               - Fibonacci.java
		               - IfElseTest.java
		               - IntCount.java
		               - Primes.java
		               - SwitchTest.java
		               - VampireNumbers.java
		          - Exceptions
		               - CADSystem16.java
		               - Ex1(1).java
		               - Ex10(2).java
		               - Ex11(2).java
		               - Ex13.java
		               - Ex2(2).java
		               - Ex21(1).java
		               - Ex25(1).java
		               - Ex27.java
		               - Ex28(1).java
		               - Ex3(1).java
		               - Ex4(2).java
		               - Ex5(1).java
		               - Ex6(2).java
		               - Ex7(1).java
		               - Ex8(2).java
		               - Ex9(1).java
		               - FailingConstructor22.java
		               - FailingConstructor22b.java
		               - FailingConstructor23.java
		               - FailingConstructor23b.java
		               - FailingConstructor24b.java
		               - Frog17(1).java
		               - Human30.java
		               - LostMessage18.java
		               - LostMessageFound19.java
		               - MainException26.java
		               - OnOffSwitch14.java
		               - Sequence12.java
		               - StormyInning20.java
		               - StormyInning29.java
		               - WithFinally15.java
		          - Generics
		               - Apply40.java
		               - BasicGeneratorDemo14.java
		               - CargoShip19.java
		               - CheckedList35.java
		               - ClassTypeCapture21.java
		               - ClassTypeCapture24.java
		               - ClassTypeCreator22.java
		               - CoffeeDecoration38.java
		               - DynamicProxyMixin39.java
		               - Ex20(1).java
		               - Ex25(3).java
		               - Ex26(1).java
		               - Ex27(1).java
		               - Ex28(2).java
		               - Ex29(1).java
		               - Ex34.java
		               - FactoryConstraint23.java
		               - Fibonacci7.java
		               - Fill41.java
		               - Functional42.java
		               - Generators13.java
		               - GenericCast32.java
		               - GenericCast33.java
		               - GenericMethods10.java
		               - GenericMethods9.java
		               - GenericSequence.java
		               - Holder3.java
		               - HolderEx2.java
		               - Holders30.java
		               - LimitsOfInference12.java
		               - LinkedStack5.java
		               - Mixins37.java
		               - MultipleInterfaceVariants31.java
		               - New11.java
		               - Ocean18.java
		               - RandomList6.java
		               - Sets17.java
		               - SixTupleTest.java
		               - StoryCharacterGenerator.java
		               - ThrowGenericException36.java
		               - TupleTest15.java
		               - TupleTest16.java
		          - Holding
		               - CollectionSequence30.java
		               - Controller13.java
		               - Ex1.java
		               - Ex11(1).java
		               - Ex12.java
		               - Ex12a.java
		               - Ex14(1).java
		               - Ex15(2).java
		               - Ex18(1).java
		               - Ex19(1).java
		               - Ex2(1).java
		               - Ex24.java
		               - Ex24b.java
		               - Ex25.java
		               - Ex26.java
		               - Ex28.java
		               - Ex29.java
		               - Ex4(1).java
		               - Ex5.java
		               - Ex6(1).java
		               - Ex7.java
		               - Ex8(1).java
		               - Gerbils17.java
		               - NonCollectionSequence32.java
		               - Queue27.java
		               - RandomShapeGenerator31.java
		               - Rodent10.java
		               - Sequence3.java
		               - Sequence9.java
		               - Statistics23.java
		               - UniqueWords21.java
		               - UniqueWords22.java
		               - Vowels16.java
		               - Vowels20.java
		          - Initialization
		               - AutomaticConstructor.java
		               - BankTest.java
		               - ConstructorTest.java
		               - ConstructorTest2.java
		               - DefaultConstructorTest.java
		               - DefaultConstructorTest2.java
		               - DogTalk.java
		               - DogTalk2.java
		               - EnumEx21.java
		               - ExplicitStatic.java
		               - ExplicitStaticEx.java
		               - InitTest17.java
		               - InitTest18.java
		               - InstanceClauseTest.java
		               - OverloadedConstructors.java
		               - PassingThisEx.java
		               - StringArrayEx16.java
		               - TankTest.java
		               - TerminationConditionEx.java
		               - VarargEx19.java
		               - VarargEx20.java
		               - Wallet.java
		          - Innerclasses
		               - Adventure12.java
		               - Cycles(1).java
		               - Ex10(1).java
		               - Ex11.java
		               - Ex15(1).java
		               - Ex18.java
		               - Ex19.java
		               - Ex20.java
		               - Ex21.java
		               - Ex23.java
		               - Ex6.java
		               - Ex9.java
		               - Games17.java
		               - GreenhouseController24.java
		               - GreenhouseController25.java
		               - GreenhouseControls24.java
		               - GreenhouseControls25.java
		               - HorrorShow14.java
		               - OtherOuter.java
		               - Outer1.java
		               - Outer12.java
		               - Outer13.java
		               - Outer3.java
		               - Outer7.java
		               - Outer8.java
		               - SecondOuter.java
		               - Sequence2.java
		               - Sequence4.java
		          - Interfaces
		               - AdaptedRandomChars16.java
		               - Cycles.java
		               - DiamondInheritance13.java
		               - Ex14.java
		               - Ex15.java
		               - Ex17.java
		               - Ex2.java
		               - Ex3.java
		               - Ex4.java
		               - Games19.java
		               - Music10.java
		               - Music9.java
		               - Rodent1.java
		               - Rodent7.java
		               - Sandwich8.java
		               - StringMixerProcessor.java
		               - TestEx5.java
		               - TestEx6.java
		          - Object
		               - ATNTest.java
		               - AutoboxTest.java
		               - CommandArgTest.java
		               - DataOnlyTest.java
		               - DocTest.java
		               - Documentation1.java
		               - Documentation2.java
		               - Documentation3.java
		               - Documentation4.java
		               - DOTest2.java
		               - HelloDocTest.java
		               - HelloWorld.java
		               - ITest.java
		               - OneStaticTest.java
		               - Overloading.java
		               - PrimitiveTest.java
		               - Rainbow.java
		               - StorageTest.java
		          - Operators
		               - Assign.java
		               - BinaryTest.java
		               - CharBinaryTest.java
		               - CoinToss.java
		               - DogCompare.java
		               - DogTest.java
		               - LongValues.java
		               - MinMax.java
		               - PassObject2.java
		               - PrintTest.java
		               - RightShiftTest.java
		               - RightShiftTest2.java
		               - StringCompare.java
		               - VelocityTester.java
		          - Polymorphism
		               - Biking.java
		               - Biking17.java
		               - Biking5.java
		               - Ex10.java
		               - Music6.java
		               - Music7.java
		               - Music8.java
		               - PolyConstructors15.java
		               - ReferenceCounting13.java
		               - Rodent12.java
		               - Rodent14.java
		               - Rodent9.java
		               - Sandwich11.java
		               - Shapes.java
		               - Shapes3.java
		               - Shapes4.java
		               - Transmogrify16.java
		          - Reusing
		               - Airplane.java
		               - BlankFinalEx.java
		               - C.java
		               - C7.java
		               - Car.java
		               - Cartoon2.java
		               - Chess.java
		               - DetergentDelegation.java
		               - Device.java
		               - Difference.java
		               - E.java
		               - Ex8.java
		               - FinalOverrideEx.java
		               - FinalOverridingIllusionEx.java
		               - Frog.java
		               - Frog17.java
		               - JurassicEx.java
		               - LoadClass.java
		               - Overload.java
		               - Scarab.java
		               - Stem.java
		               - Stem10.java
		               - Stem2.java
		          - Strings
		               - Ex17(1).java
		               - Ex18(2).java
		               - Ex19(2).java
		               - Ex5(2).java
		               - Ex6(3).java
		               - Groups12.java
		               - InfiniteRecursion2.java
		               - JGrep15.java
		               - JGrep16.java
		               - Receipt4.java
		               - RegEx11.java
		               - Replacing9.java
		               - Scanner20.java
		               - Sentence7.java
		               - SplitDemo14.java
		               - Splitting8.java
		               - SprinklerSystem1.java
		               - StartEnd13.java
		               - TestRegularExpression10.java
		               - TestRegularExpression11.java
		               - Turtle3.java
		          - TypeInfo
		               - ClassInfo.java
		               - Ex10(3).java
		               - Ex11(3).java
		               - Ex12(1).java
		               - Ex13(1).java
		               - Ex8(3).java
		               - Ex9(2).java
		               - Finder25.java
		               - Music26.java
		               - RegisteredClasses14.java
		               - RegisteredCoffeeFactories16.java
		               - RegisteredFactories24.java
		               - RegisteredPetFactories15.java
		               - Shapes3(1).java
		               - Shapes4(1).java
		               - Shapes5.java
		               - Shapes6.java
		               - ShowMethods17.java
		               - ShowMethods18.java
		               - SimpleDynamicProxy22.java
		               - SimpleDynamicProxy23.java
		               - SimpleProxyDemo21.java
		               - SweetShop7.java
		               - ToyTest1.java
		               - ToyTest19.java
		               - ToyTest2.java
		
		
		
		====== Queue ======
		ThinkingInJava
		.DS_Store
		Java编程思想(第4版).pdf
		The_Thinking_in_Java_Annotated_Solution_Guide(TIJ4-solutions).pdf
		Thinking in Java 第4版 源代码
		Thinking In Java 第四版(英文版)-练习题答案
		.DS_Store
		access
		annotations
		arrays
		bangbean
		build.xml
		chapterOrder.xml
		concurrency
		containers
		control
		Copyright.txt
		DEclipse.py
		Eclipse.py
		enumerated
		exceptions
		FindBugsExcluder.py
		FindBugsFilter.xml
		frogbean
		generics
		gui
		holding
		initialization
		innerclasses
		interfaces
		io
		JavaLint.py
		net
		object
		operators
		OutputGenerator.py
		OutputVerifier.py
		polymorphism
		RedundantImportDetector.py
		reusing
		strings
		swt
		typeinfo
		xml
		.DS_Store
		Access
		Arrays
		Containers
		Control
		Exceptions
		Generics
		Holding
		Initialization
		Innerclasses
		Interfaces
		Object
		Operators
		Polymorphism
		Reusing
		Strings
		TypeInfo
		build.xml
		Cake.java
		ChocolateChip.java
		ChocolateChip2.java
		cookie2
		dessert
		Dinner.java
		FullQualification.java
		IceCream.java
		ImportedMyClass.java
		LibTest.java
		Lunch.java
		mypackage
		OrganizedByAccess.java
		Pie.java
		PrintTest.java
		QualifiedMyClass.java
		SingleImport.java
		AtUnitComposition.java
		AtUnitExample1.java
		AtUnitExample2.java
		AtUnitExample3.java
		AtUnitExample4.java
		AtUnitExample5.java
		AtUnitExternalTest.java
		build.xml
		database
		ExtractInterface.java
		HashSetTest.java
		InterfaceExtractorProcessor.java
		InterfaceExtractorProcessorFactory.java
		Multiplier.java
		PasswordUtils.java
		SimulatingNull.java
		StackL.java
		StackLStringTest.java
		Testable.java
		UseCase.java
		UseCaseTracker.java
		AlphabeticSearch.java
		ArrayOfGenerics.java
		ArrayOfGenericType.java
		ArrayOptions.java
		ArraySearching.java
		AssemblingMultidimensionalArrays.java
		AutoboxingArrays.java
		build.xml
		ComparatorTest.java
		ComparingArrays.java
		CompType.java
		ContainerComparison.java
		CopyingArrays.java
		FillingArrays.java
		GeneratorsTest.java
		IceCream.java
		MultidimensionalObjectArrays.java
		MultidimensionalPrimitiveArray.java
		MultiDimWrapperArray.java
		ParameterizedArrayType.java
		PrimitiveConversionDemonstration.java
		RaggedArray.java
		RandomGeneratorsTest.java
		Reverse.java
		StringSorting.java
		TestArrayGeneration.java
		TestGenerated.java
		ThreeDWithNew.java
		BangBean.java
		BangBeanTest.java
		build.xml
		.DS_Store
		ActiveObjectDemo.java
		AtomicEvenGenerator.java
		AtomicIntegerTest.java
		Atomicity.java
		AtomicityTest.java
		AttemptLocking.java
		BankTellerSimulation.java
		BasicThreads.java
		build.xml
		CachedThreadPool.java
		CallableDemo.java
		CaptureUncaughtException.java
		CarBuilder.java
		Chopstick.java
		CloseResource.java
		CountDownLatchDemo.java
		CriticalSection.java
		DaemonFromFactory.java
		Daemons.java
		DaemonsDontRunFinally.java
		DeadlockingDiningPhilosophers.java
		DelayQueueDemo.java
		EvenChecker.java
		EvenGenerator.java
		ExceptionThread.java
		ExchangerDemo.java
		ExplicitCriticalSection.java
		FastSimulation.java
		Fat.java
		FixedDiningPhilosophers.java
		FixedThreadPool.java
		GreenhouseScheduler.java
		HorseRace.java
		Interrupting.java
		Interrupting2.java
		InterruptingIdiom.java
		IntGenerator.java
		Joining.java
		LiftOff.java
		ListComparisons.java
		MainThread.java
		MapComparisons.java
		MoreBasicThreads.java
		MultiLock.java
		MutexEvenGenerator.java
		NaiveExceptionHandling.java
		NIOInterruption.java
		NotifyVsNotifyAll.java
		OrnamentalGarden.java
		Philosopher.java
		PipedIO.java
		Pool.java
		PriorityBlockingQueueDemo.java
		ReaderWriterList.java
		ResponsiveUI.java
		Restaurant.java
		restaurant2
		SelfManaged.java
		SemaphoreDemo.java
		SerialNumberChecker.java
		SerialNumberGenerator.java
		SettingDefaultHandler.java
		SimpleDaemons.java
		SimpleMicroBenchmark.java
		SimplePriorities.java
		SimpleThread.java
		SingleThreadExecutor.java
		SleepingTask.java
		SynchronizationComparisons.java
		SynchronizedEvenGenerator.java
		SyncObject.java
		TestBlockingQueues.java
		Tester.java
		ThreadLocalVariableHolder.java
		ThreadVariations.java
		ToastOMatic.java
		waxomatic
		waxomatic2
		AssociativeArray.java
		Bits.java
		build.xml
		CanonicalMapping.java
		CollectionDataGeneration.java
		CollectionDataTest.java
		CollectionMethods.java
		CountedString.java
		DequeTest.java
		Enumerations.java
		FailFast.java
		FillingLists.java
		Groundhog.java
		Groundhog2.java
		IndividualTest.java
		LinkedHashMapDemo.java
		ListPerformance.java
		Lists.java
		ListSortSearch.java
		MapDataTest.java
		MapEntry.java
		MapPerformance.java
		Maps.java
		Prediction.java
		QueueBehavior.java
		RandomBounds.java
		ReadOnly.java
		References.java
		SetPerformance.java
		SimpleHashMap.java
		SlowMap.java
		SortedMapDemo.java
		SortedSetDemo.java
		SpringDetector.java
		SpringDetector2.java
		Stacks.java
		StringHashCode.java
		Synchronization.java
		Test.java
		Tester.java
		TestParam.java
		ToDoList.java
		TypesForSets.java
		Unsupported.java
		Utilities.java
		BreakAndContinue.java
		build.xml
		CommaOperator.java
		ForEachFloat.java
		ForEachInt.java
		ForEachString.java
		IfElse.java
		IfElse2.java
		LabeledFor.java
		LabeledWhile.java
		ListCharacters.java
		VowelsAndConsonants.java
		WhileTest.java
		AlarmPoints.java
		BigEnumSet.java
		build.xml
		Burrito.java
		cartoons
		CarWash.java
		Competitor.java
		ConstantSpecificMethod.java
		EnumClass.java
		EnumMaps.java
		EnumSets.java
		Input.java
		menu
		NonEnum.java
		NotClasses.java
		Outcome.java
		OverrideConstantSpecific.java
		OzWitch.java
		PostOffice.java
		RandomTest.java
		Reflection.java
		RoShamBo.java
		RoShamBo1.java
		RoShamBo2.java
		RoShamBo3.java
		RoShamBo4.java
		RoShamBo5.java
		RoShamBo6.java
		SecurityCategory.java
		SpaceShip.java
		Spiciness.java
		TrafficLight.java
		UpcastEnum.java
		VendingMachine.java
		VendingMachineInput.txt
		AlwaysFinally.java
		build.xml
		Cleanup.java
		CleanupIdiom.java
		DynamicFields.java
		ExceptionMethods.java
		ExceptionSilencer.java
		ExtraFeatures.java
		FinallyWorks.java
		FullConstructors.java
		Human.java
		InheritingExceptions.java
		InputFile.java
		LoggingExceptions.java
		LoggingExceptions2.java
		LostMessage.java
		MainException.java
		MultipleReturns.java
		NeverCaught.java
		OnOffException1.java
		OnOffException2.java
		OnOffSwitch.java
		Rethrowing.java
		RethrowNew.java
		StormyInning.java
		Switch.java
		TurnOffChecking.java
		WhoCalled.java
		WithFinally.java
		build.xml
		Frog.java
		Apply.java
		ArrayMaker.java
		ArrayOfGeneric.java
		ArrayOfGenericReference.java
		BankTeller.java
		BasicBounds.java
		BasicGeneratorDemo.java
		BasicHolder.java
		build.xml
		ByteSet.java
		CaptureConversion.java
		CheckedList.java
		ClassCasting.java
		ClassTypeCapture.java
		coffee
		ComparablePet.java
		CompilerIntelligence.java
		CountedObject.java
		CovariantArrays.java
		CovariantReturnTypes.java
		CreatorGeneric.java
		CRGWithBasicHolder.java
		CuriouslyRecurringGeneric.java
		decorator
		DogsAndRobots.cpp
		DogsAndRobots.java
		DynamicProxyMixin.java
		EpicBattle.java
		Erased.java
		ErasedTypeEquivalence.java
		ErasureAndInheritance.java
		ExplicitTypeSpecification.java
		FactoryConstraint.java
		Fibonacci.java
		Fill.java
		Fill2.java
		FilledListMaker.java
		Functional.java
		Generators.java
		GenericArray.java
		GenericArray2.java
		GenericArrayWithTypeToken.java
		GenericCast.java
		GenericHolder.java
		GenericMethods.java
		GenericReading.java
		GenericsAndCovariance.java
		GenericsAndReturnTypes.java
		GenericVarargs.java
		GenericWriting.java
		HasF.java
		HijackedInterface.java
		Holder.java
		Holder1.java
		Holder2.java
		Holder3.java
		InheritBounds.java
		InstantiateGenericType.cpp
		InstantiateGenericType.java
		IterableFibonacci.java
		LatentReflection.java
		LimitsOfInference.java
		LinkedStack.java
		ListMaker.java
		ListOfGenerics.java
		ListOfInt.java
		LostInformation.java
		Manipulation.java
		Manipulator2.java
		Manipulator3.java
		Mixins.cpp
		Mixins.java
		MultipleInterfaceVariants.java
		NeedCasting.java
		NonCovariantGenerics.java
		NotSelfBounded.java
		OrdinaryArguments.java
		Performs.java
		PlainGenericInheritance.java
		PrimitiveGenericTest.java
		RandomList.java
		RestrictedComparablePets.java
		ReturnGenericType.java
		SelfBounding.java
		SelfBoundingAndCovariantArguments.java
		SelfBoundingMethods.java
		SimpleDogsAndRobots.java
		SimpleHolder.java
		SimpleQueue.java
		SimplerPets.java
		Store.java
		SuperTypeWildcards.java
		Templates.cpp
		ThrowGenericException.java
		TupleList.java
		TupleTest.java
		TupleTest2.java
		UnboundedWildcards1.java
		UnboundedWildcards2.java
		Unconstrained.java
		UseList.java
		UseList2.java
		watercolors
		WatercolorSets.java
		Wildcards.java
		BangBean2.java
		BeanDumper.java
		BorderLayout1.java
		....

	 */
	
}

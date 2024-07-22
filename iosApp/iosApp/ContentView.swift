import SwiftUI
import Shared

struct ContentView: View {
    @State var viewModel = RocketLaunchViewModel()
    @State var state: RocketLaunchViewModel.UiState = RocketLaunchViewModel.UiState(isLoading: true, error: nil, launches: [])
    var body: some View {
        NavigationView {
            listView()
            .navigationBarTitle("SpaceX Launches")
            .navigationBarItems(trailing:
                Button("Reload") {
                    viewModel.loadLaunches()
            })
        }.task {
            for await state in viewModel.state {
                self.state = state
            }
        }
    }

    private func listView() -> AnyView {
        if (state.isLoading) {
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        } else if (state.error != nil) {
            return AnyView(Text(state.error ?? "").multilineTextAlignment(.center))
        }
        return AnyView(List(state.launches) { launch in
            RocketLaunchRow(rocketLaunch: launch)
        })
    }
}

extension RocketLaunchModel: Identifiable {
    public typealias ID = Int
    public var id: Int {
        return hash
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

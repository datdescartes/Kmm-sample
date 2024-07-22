//
//  RocketLaunchRow.swift
//  iosApp
//
//  Created by datdescartes on 2024/07/22.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct RocketLaunchRow: View {
    var rocketLaunch: RocketLaunchModel

    var body: some View {
        HStack() {
            VStack(alignment: .leading, spacing: 10.0) {
                Text("\(rocketLaunch.missionName) - \(String(rocketLaunch.launchDate.year))").font(.system(size: 18)).bold()
                Text(launchText).foregroundColor(launchColor)
                Text("Launch year: \(String(rocketLaunch.launchDate.year))")
                Text("\(rocketLaunch.details ?? "")")
            }
            Spacer()
        }
    }
}

extension RocketLaunchRow {
    private var launchText: String {
        if let isSuccess = rocketLaunch.launchSuccess {
            return isSuccess.boolValue ? "Successful" : "Unsuccessful"
        } else {
            return "No data"
        }
    }

    private var launchColor: Color {
        if let isSuccess = rocketLaunch.launchSuccess {
            return isSuccess.boolValue ? Color.green : Color.red
        } else {
            return Color.gray
        }
    }
}

#Preview {
    RocketLaunchRow(rocketLaunch: RocketLaunchModel(flightNumber: 123, missionName: "mission name", details: "details", launchSuccess: true, launchDate: Kotlinx_datetimeLocalDateTime(year: 2020, month: Kotlinx_datetimeMonth.april, dayOfMonth: 1, hour: 1, minute: 1, second: 1, nanosecond: 1), patchUrlSmall: nil, patchUrlLarge: nil, articleUrl: nil))
}
